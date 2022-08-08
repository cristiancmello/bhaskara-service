package com.bhaskara.service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClientBuilder;
import com.amazonaws.services.cloudformation.model.CreateStackRequest;
import com.amazonaws.services.cloudformation.model.DeleteStackRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BhaskaraCfTests {
    @AfterEach
    void cleanup() {
        var cfClient = AmazonCloudFormationClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .build();

        var deleteStackRequest = new DeleteStackRequest();
        deleteStackRequest.setStackName("bhaskara-stack");
        cfClient.deleteStack(deleteStackRequest);
    }

    @Test
    void givenCfFileAndEcrContainerRegistry_whenDeploy_thenEcsFargateClusterDeployment() {
        // TODO: Criar arquivo Cloudformation que define resources: ECS Cluster, TaskDefinition, Service
        // TaskDefinition: https://docs.aws.amazon.com/pt_br/AWSCloudFormation/latest/UserGuide/aws-resource-ecs-taskdefinition.html
        // Service: https://docs.aws.amazon.com/pt_br/AWSCloudFormation/latest/UserGuide/aws-resource-ecs-service.html
        // TODO: Importar SDK ECS para montar testes de integração para saber se resources foram devidamente criados
        // TODO: Refatorar para extrair comando de deploy de infra AWS
        var cfClient = AmazonCloudFormationClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .build();

        var createStackRequest = new CreateStackRequest();
        createStackRequest.setStackName("bhaskara-stack");
        createStackRequest.setTemplateBody("AWSTemplateFormatVersion: 2010-09-09\n" +
                "\n" +
                "Resources:\n" +
                "  ECSCluster:\n" +
                "    Type: 'AWS::ECS::Cluster'\n" +
                "    Properties:\n" +
                "      ClusterName: BhaskaraCluster"); // TODO: carregar string do arquivo BhaskaraCloudformationStack.yml

        cfClient.createStack(createStackRequest);
    }
}
