package com.bhaskara.service;

import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ecr.AmazonECR;
import com.amazonaws.services.ecr.AmazonECRClientBuilder;
import com.amazonaws.services.ecr.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.command.PushImageResultCallback;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class BhaskaraOpsTests {
    static final String imageName = "bhaskara-service";
    static final String repositoryName = "bhaskara-service";
    static final String tagName = "latest";

    @Container
    static GenericContainer bhaskaraServiceContainer = new GenericContainer(
            new ImageFromDockerfile(imageName, true)
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
        assertThat(bhaskaraServiceContainer.getDockerImageName()).isEqualTo(imageName + ":" + tagName);
    }

    @Test
    void givenDockerImage_whenDeploy_thenPublishImageAwsEcrRegistry() throws InterruptedException {
        AmazonECR amazonECR = AmazonECRClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .build();

        DescribeRepositoriesRequest describeRepositoriesRequest = new DescribeRepositoriesRequest();
        describeRepositoriesRequest.setRepositoryNames(List.of(repositoryName));

        DescribeRepositoriesResult describeRepositoriesResult = amazonECR.describeRepositories(describeRepositoriesRequest);
        var repositories = describeRepositoriesResult.getRepositories();
        var repository = repositories.get(0);

        GetAuthorizationTokenResult authorizationToken = amazonECR
                .getAuthorizationToken(new GetAuthorizationTokenRequest().withRegistryIds(repository.getRegistryId()));

        List<AuthorizationData> authorizationData = authorizationToken.getAuthorizationData();
        String encodedToken = authorizationData.get(0).getAuthorizationToken();

        String userPassword = StringUtils.newStringUtf8(Base64.decodeBase64(encodedToken));
        String username = userPassword.substring(0, userPassword.indexOf(":"));
        String password = userPassword.substring(userPassword.indexOf(":") + 1);

        DockerClientConfig standard = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withRegistryUrl(authorizationData.get(0).getProxyEndpoint())
                .withRegistryUsername(username)
                .withRegistryPassword(password)
                .build();

        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(standard.getDockerHost())
                .sslConfig(standard.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();

        var dockerClient = DockerClientImpl.getInstance(standard, httpClient);

        dockerClient.tagImageCmd(imageName, repository.getRepositoryUri(), tagName).exec();

        dockerClient
                .pushImageCmd(repository.getRepositoryUri())
                .withTag(tagName)
                .exec(new PushImageResultCallback())
                .awaitCompletion();

        var describeImagesRequest = new DescribeImagesRequest();
        var filter = new DescribeImagesFilter();
        var imageIdentifier = new ImageIdentifier();

        imageIdentifier.setImageTag(tagName);
        filter.setTagStatus("TAGGED");

        describeImagesRequest.setRepositoryName(repositoryName);
        describeImagesRequest.setFilter(filter);
        describeImagesRequest.setImageIds(List.of(imageIdentifier));

        var imageDetail = amazonECR.describeImages(describeImagesRequest).getImageDetails().get(0);
        var imageSizeInBytesFromEcr = imageDetail.getImageSizeInBytes();
        var imageSizeInBytesFromLocal = dockerClient.inspectImageCmd(repository.getRepositoryUri()).exec().getSize();

        assertThat(imageSizeInBytesFromEcr).isBetween(1L, imageSizeInBytesFromLocal);
    }
}
