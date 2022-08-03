package com.bhaskara.service;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;

@Testcontainers
public class BhaskaraOpsTests {
    @Container
    GenericContainer bhaskaraServiceContainer = new GenericContainer(
            new ImageFromDockerfile("bhaskara-service/bhaskara-back", true)
                    .withFileFromPath(".", Path.of(".")))
            .waitingFor(Wait.forLogMessage(".*Started ServiceApplication.*", 1));

    @BeforeEach
    void setup() {
        bhaskaraServiceContainer.start();
    }

    @Test
    void givenDockerfile_whenBuildImage_thenExposedPortAndDockerImageName() {
        var exposedPorts = bhaskaraServiceContainer.getContainerInfo()
                .getNetworkSettings().getPorts().toPrimitive();

        assertThat(exposedPorts.keySet()).containsOnly("8080/tcp");
        assertThat(bhaskaraServiceContainer.getDockerImageName()).isEqualTo("bhaskara-service/bhaskara-back:latest");
    }

    @Test
    void givenDockerImage_whenDeploy_thenPublishImageAwsEcrRegistry() {
        // TODO
    }
}
