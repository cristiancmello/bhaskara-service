package com.bhaskara.service;

import java.nio.file.Path;
import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.command.PushImageResultCallback;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class BhaskaraOpsTests {
    @Container
    static
    GenericContainer bhaskaraServiceContainer = new GenericContainer(
            new ImageFromDockerfile("cristiancmello/bhaskara-service", true)
                    .withFileFromPath(".", Path.of(".")))
            .waitingFor(Wait.forLogMessage(".*Started ServiceApplication.*", 1));

    @BeforeAll
    static void setup() {
        bhaskaraServiceContainer.start();
    }

    @Test
    void givenDockerfile_whenBuildImage_thenExposedPortAndDockerImageName() {
        var exposedPorts = bhaskaraServiceContainer.getContainerInfo()
                .getNetworkSettings().getPorts().toPrimitive();

        assertThat(exposedPorts.keySet()).containsOnly("8080/tcp");
        assertThat(bhaskaraServiceContainer.getDockerImageName()).isEqualTo("cristiancmello/bhaskara-service:latest");
    }

    @Test
    void givenDockerImage_whenDeploy_thenPublishImageAwsEcrRegistry() throws InterruptedException {
        // TODO: push image to ECR Registry
        DockerClientConfig standard = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .build();

        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(standard.getDockerHost())
                .sslConfig(standard.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();

        var dockerClient = DockerClientImpl.getInstance(standard, httpClient);

        dockerClient
                .pushImageCmd("cristiancmello/bhaskara-service")
                .withName("cristiancmello/bhaskara-service")
                .withTag("latest")
                .exec(new PushImageResultCallback())
                .awaitCompletion();

//        var dockerClient = DockerClientBuilder.getInstance().build();
//
//        AmazonECR amazonECR = AmazonECRClientBuilder.standard()
//                .withRegion(Regions.US_EAST_1)
//                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
//                .build();
//
//        GetAuthorizationTokenResult authorizationToken = amazonECR
//                .getAuthorizationToken(new GetAuthorizationTokenRequest());
//
//        List<AuthorizationData> authorizationData = authorizationToken.getAuthorizationData();
//        String encodedToken = authorizationData.get(0).getAuthorizationToken();
//
//        System.out.println(dockerClient.pushImageCmd("7c7035048121"));

//        ListImagesRequest request = new ListImagesRequest().withRepositoryName("bhaskara-service");
//        ListImagesResult response = amazonECR.listImages(request);
//
//        System.out.println(bhaskaraServiceContainer.getContainerInfo().);
//
//        PutImageRequest putImageRequest = new PutImageRequest();
//        putImageRequest.setImageManifest("");
//
//        amazonECR.putImage(putImageRequest);
//
//        System.out.println(response.getImageIds());
    }
}
