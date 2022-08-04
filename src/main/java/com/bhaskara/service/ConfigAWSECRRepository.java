package com.bhaskara.service;

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

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ConfigAWSECRRepository {

    public static AmazonECR initializeAmazonECRClient() {
        return AmazonECRClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .build();
    }

    public static Repository getECRRepository(AmazonECR amazonECR, String repositoryName) {
        DescribeRepositoriesRequest describeRepositoriesRequest = new DescribeRepositoriesRequest();
        describeRepositoriesRequest.setRepositoryNames(List.of(repositoryName));

        DescribeRepositoriesResult describeRepositoriesResult = amazonECR.describeRepositories(describeRepositoriesRequest);
        var repositories = describeRepositoriesResult.getRepositories();
        return repositories.get(0);
    }

    public static AuthorizationData getAuthorizationData(AmazonECR amazonECR, Repository repository) {
        GetAuthorizationTokenResult authorizationToken = amazonECR
                .getAuthorizationToken(new GetAuthorizationTokenRequest().withRegistryIds(repository.getRegistryId()));

        return authorizationToken.getAuthorizationData().get(0);
    }

    public static Map<String, String> getCredentials(AuthorizationData authorizationData) {
        String encodedToken = authorizationData.getAuthorizationToken();
        String userPassword = StringUtils.newStringUtf8(Base64.decodeBase64(encodedToken));
        String username = userPassword.substring(0, userPassword.indexOf(":"));
        String password = userPassword.substring(userPassword.indexOf(":") + 1);

        return Map.of("username", username, "password", password);
    }

    public static DockerClient initializeDockerClient(AmazonECR amazonECR, Repository repository) {
        AuthorizationData authorizationData = getAuthorizationData(amazonECR, repository);
        Map<String, String> credentials = getCredentials(authorizationData);

        DockerClientConfig standard = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withRegistryUrl(authorizationData.getProxyEndpoint())
                .withRegistryUsername(credentials.get("username"))
                .withRegistryPassword(credentials.get("password"))
                .build();

        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(standard.getDockerHost())
                .sslConfig(standard.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();

        return DockerClientImpl.getInstance(standard, httpClient);
    }

    public static void createRepository(DockerClient dockerClient, Repository repository, String imageName,
                                        String tagName) throws InterruptedException {

        dockerClient.tagImageCmd(imageName, repository.getRepositoryUri(), tagName).exec();

        // Then publish image in Registry
        dockerClient
                .pushImageCmd(repository.getRepositoryUri())
                .withTag(tagName)
                .exec(new PushImageResultCallback())
                .awaitCompletion();
    }
}
