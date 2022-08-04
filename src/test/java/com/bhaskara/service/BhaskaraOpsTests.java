package com.bhaskara.service;

import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ecr.AmazonECR;
import com.amazonaws.services.ecr.AmazonECRClientBuilder;
import com.amazonaws.services.ecr.model.*;
import com.github.dockerjava.api.DockerClient;
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
        AmazonECR amazonECR = ConfigAWSECRRepository.initializeAmazonECRClient();
        Repository repository = ConfigAWSECRRepository.getECRRepository(amazonECR, repositoryName);
        DockerClient dockerClient = ConfigAWSECRRepository.initializeDockerClient(amazonECR, repository);

        var describeImagesRequest = new DescribeImagesRequest();
        var filter = new DescribeImagesFilter();
        var imageIdentifier = new ImageIdentifier();

        imageIdentifier.setImageTag(tagName);
        filter.setTagStatus("TAGGED");

        describeImagesRequest.setRepositoryName(repositoryName);
        describeImagesRequest.setFilter(filter);
        describeImagesRequest.setImageIds(List.of(imageIdentifier));

        ConfigAWSECRRepository.createRepository(dockerClient, repository, imageName, tagName);

        var imageDetail = amazonECR.describeImages(describeImagesRequest).getImageDetails().get(0);
        var imageSizeInBytesFromEcr = imageDetail.getImageSizeInBytes();
        var imageSizeInBytesFromLocal = dockerClient.inspectImageCmd(repository.getRepositoryUri()).exec().getSize();

        assertThat(imageSizeInBytesFromEcr).isBetween(1L, imageSizeInBytesFromLocal);
    }
}
