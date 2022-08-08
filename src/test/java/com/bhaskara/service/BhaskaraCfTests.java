package com.bhaskara.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BhaskaraCfTests {
    @Test
    void givenCfFileAndEcrContainerRegistry_whenDeploy_thenEcsFargateClusterDeployment() {
        // TODO: Criar arquivo Cloudformation que define resources: ECS Cluster, TaskDefinition, Service
        // TODO: Importar SDK ECS para montar testes de integração para saber se resources foram devidamente criados
        // TODO: Refatorar para extrair comando de deploy de infra AWS
    }
}
