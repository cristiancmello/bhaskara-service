# Baskhara Service

O **professor de matemática Estevão** veio até a nós pelo nosso canal de contato com o cliente em busca de uma **solução de software**
para uma **visão** de negócio que teve com seus vários anos de experiência de sala de aula. Nos apresentou sua visão e uma proposta
de que podemos ser seus sócios neste empreendimento. Solicitamos uma reunião em nosso escritório e ele compareceu.

Apaixonado pelo conhecimento e pela experimentação dos conceitos matemáticos para a vida real, Estevão nos explicou um problema típico
para alunos do Ensino Fundamental e Médio: a resolução de equações do segundo grau. Ele queria, com isso, mostrar como um aplicativo
poderia servir aos alunos como um meio lúdico e simples de se exercitar polinômios e achar raízes de uma dada função do segundo grau.

Ele aposta que o software tem a capacidade de fornecer um passo-a-passo da execução de um algoritmo muito usado para encontrar raízes
reais, sendo a popular "Fórmula de Bhaskara". Esta fórmula fornece procedimentos e estes podemos enxergar como um algoritmo.

## Análise Computacional e Matemática da Fórmula de Bhaskara

Com a nossa experiência, conversamos de que uma solução algorítmica **existe**, porque o algoritmo de bhaskara é computável, isto é, tem tempo finito
e dá respostas corretas. Concluímos, após a análise computacional, ser **possível implementar tal solução**.

> Obs.: Dependendo do tipo de solução, por vezes buscamos profissionais capacitados em diferentes áreas do conhecimento.

Definimos com o professor a **Função do Segundo Grau**, que chamamos de forma original 

```
f(x) = ax^2 + bx + c
```

Com **a, b** e **c** pertencente aos **números reais**. Por sua vez, **Encontrar raízes** é o ato de definir

```
f(x) = ax^2 + bx + c = 0
```

Para achar as raízes, ou seja, os valores de 'x' que satisfaça `f(x) = 0` ou `y = 0`, definimos o Algoritmo de Bhaskara

```
delta = b^2 - 4*a*c

Conjunto de Raizes (popularmente x1 e x2) = (-b +- sqrt(delta)) / 2*a
```

### Caso de `a = 0`

O prof. Estevão nos avisou desta situação. Com `a = 0`, 

```
Conjunto de Raizes = (-b +- sqrt(delta)) / 2*0
faz com que Conjunto de Raizes = Não Exista em Números Reais. Logo, podemos já impedir o prosseguimento do algoritmo muito antes
de executá-lo, somente testando 'a' sendo diferente de 0. Ele disse que é suficiente indicar "Por favor, Bhaskara só funciona com 'a' diferente de zero."
```

As constantes **b** e **c** pode assumir quaisquer valores reais. Concluímos que

* **a** : não pode ser zero e deve ser um valor real
* **b** : pode ser qualquer valor real
* **c** : pode ser qualquer valor real

## Goals

O prof. Estevão nos disse que a solução de software pode trazer benefícios às escolas públicas.
Ele nos explicou que andou conversando com o secretário de educação municipal e que poderia
utilizar algumas escolas como *early adopters* (adotantes iniciais).

Confiamos nele devido sua experiência profissional e como educador. Acreditamos que alcançaremos
juntos os objetivos de negócio:

* **OBJ1**: Facilitar aos alunos e aos professores de matemática a montagem de equação do segundo grau com
diferentes valores de entrada, o que permite ao professor explicar detalhes matemáticos;

* **OBJ2**: Fazer os alunos notarem como os procedimentos de encontrar raízes pode ser fácil de executar
seguindo exemplos variados;

* **OBJ3**: Explicar aos alunos as propriedades matemáticas da função. 

* **OBJ4**: Universalizar o acesso à solução.

## Capabilities

Após intensas reuniões, Estevão expressou quais capacidades a solução de software pode oferecer.

* **CAP001** - Fazer o cálculo de raízes de uma função do segundo grau: esperamos alcançar **OBJ1**;
* **CAP002** - Mostrar um passo-a-passo da execução da Fórmula de Bhaskara: esperamos alcançar **OBJ2**;
* **CAP003** - Gerar um documento que detalhe as propriedades matemáticas da função: esperamos alcançar **OBJ3**.
* **CAP004** - Integração da solução com outros softwares do mercado : esperamos alcançar **OBJ4**
* **???** - ??? : esperamos alcançar **???**

Concordamos e verificamos serem todos possíveis de se fazer com a tecnologia atual.

## Features (Inicia-se a fase 'Análise de Requisitos')

Chegada a hora de informar ao Estevão quais features devemos colocar num contrato de prestação de serviço.
Foi acordado que primeiramente iremos prestar o serviço a um custo reduzido e, com o sucesso do produto,
podemos receber dividendos como sócios, algo que também foi definido no contrato.

Temos que lembrar que cada capability gera features. Uma feature detalha como iremos alcançar a capability
utilizando alguma tecnologia de software real. A análise de requisitos consiste em levantar os requisitos,
ou seja, as features.

Geralmente negociamos quais features entram no contrato e como vai ser o chamado **Road map**. Um Road map
é um mapa de entregas a serem feitas após cada ciclo de desenvolvimento. Assim faremos somente o que foi
acordado em contrato, estabelecemos um prazo de término da prestação do serviço e período de suporte técnico.

> Nesta etapa, o prof. Estevão, nossos analistas de negócio e nós desenvolvedores estaremos participando das reuniões.

### Milestones (marcos de entrega)

Adotamos como marco de entrega a publicação de pacotes com todos os binários do software compilado.
Para facilitar referências contratuais como "Até Dezembro entregaremos a versão v1.0", adotaremos o [SEMVER](https://semver.org/)
como sistema de versionamento de software.

### Feature F001: Dar suporte ao Cálculo de Raízes de Equação do Segundo Grau por Linha de Comando 

* Capability atendida: **CAP001**

* Observação: o professor disse que aceita nesta etapa do projeto que a interação com a solução ocorra somente por
linha de comando. Ele possui experiência com terminal de comandos.

* Descrição: ...

#### História de Usuário

A história de usuário é um detalhamento da feature. Uma feature pode ter várias histórias.
Lembrando-se que, se uma história é muito grande, pode ser que seja necessário criar uma nova feature.
Uma história pode ser quebrada em Exemplos. Nos exemplos já podemos utilizar o modelo de descrição do
BDD, porque tratamos do comportamento das ações que o sistema precisa ter.

Os exemplos podem produzir o chamado "Efeito de Precedência". Quando ocorre isso? Quando um exemplo
tem efeito de consequência para o exemplo seguinte.

* EXEMPLO 1

```gherkin
Scenario: dadas constantes a, b e c validas calcule as raizes.
    Given Os parametros de entrada a, b e c como sendo valores do tipo texto
    When Os parametros convertidos a partir da entrada a, b e c forem validos, isto e, sendo numeros reais
    Then A função de calcular raizes deve retornar as raizes da equacao
```

* EXEMPLO 2

```gherkin
Scenario: dadas constantes ou 'a' ou 'b' ou 'c' invalidas, indique "Por favor, insira somente numeros reais"
    Given Os parametros de entrada a, b e c como sendo valores do tipo texto
    When Alguns dos parametros forem invalidos, isto e, for inserido caracteres invalidos
    Then O cálculo de raízes deve lançar o erro 'Por favor, insira somente numeros reais'
```

* EXEMPLO 3

```gherkin
Scenario: dada constante 'a' sendo zero, indique "Nao existem raizes reais". Como observacao, nao calcule as raizes.

    Given Os parametros de entrada a, b e c como sendo valores do tipo texto
    When O parametro 'a' for zero mas b e c forem numeros reais
    Then Lance o erro 'Nao existem raizes reais'
```

* EXEMPLO 4

```gherkin
Scenario: dada um programa que faz a leitura via linha de comando do tipo REPL, digita-se
comando para calcular as raizes usando Bhaskara.

  Given o comando 'calcbhaskara'
  When o comando for executado
  And passar os coeficientes a, b e c via prompt "Insira por favor o coeficiente 'x'"
  And confirmar
  Then Calcule as raizes
  And Imprima os valores das saidas
```

* EXEMPLO 5

```gherkin
# TODO Exemplo de Historia com o tratamento de erros de digitacao de comandos
```

### Feature F002: Uma API RESTful para facilitar uso por outros softwares

* Capability atendida: **CAP004**
* Observação: definimos que esta feature é um requisito **funcional**.

### Feature F003: MathReport - um analisador matemático que gera um arquivo descrevendo o passo-a-passo do encontro de raízes e as propriedades da Equação do Segundo Grau + Grafico 

* Capability atendida: **CAP002**
* Definimos que esta feature é um requisito **funcional**.
* Descrição: 

#### Histórias de Usuário

* EXEMPLO 1:

```gherkin
Scenario: dados os coeficientes a, b e c seria gerado um grafico junto com para que assim se possa
entender a equacao.

  Given os coeficientes a, b e c
  When as raizes forem calculadas
  And o comando 'graph' for executado 
  Then um grafico é gerado e salvo num documento PDF
```

* EXEMPLO 2:

```gherkin
Scenario: dados os coeficientes a, b e c deseja-se a exibicao das propriedades da equacao.
Propriedade 1: Retornar os valores a, b e c
Propriedade 2: Valor do discriminante (delta) - delta > 0, delta = 0, delta < 0
Propriedade 3: Valores das raizes 
Propriedade 4: Coordenadas do Vértice (xv, yv)

  Given os coeficientes a, b e c
  When for solicitado a impressao do relatorio das propriedades usando o comando 'mathreport'
  Then um relatorio é gerado via documento (PDF)
```

* EXEMPLO 3:

```gherkin
Scenario: dados os coeficientes a, b e c deseja-se um relatorio que contenha o grafico da equacao
e relatorio das propriedades.

  Given os coeficientes a, b e c
  When o comando 'graph.mathreport' ou 'mathreport.graph'
  Then imprima o documento com ou grafico e as propriedades para 'graph.mathreport' ou com as propriedades e o grafico para 'mathreport.graph'
```

### Feature F004: Histórico de Expressões Matemáticas

* Capabilites atendidas: **CAP002** e **CAP003**
* Descrição: Estevão deseja buscar numa base de dados as operações já realizadas filtrando-se pelas constantes 'a', 'b' e 'c' já inseridas anteriormente.
Ele disse que se filtrarmos por 'a', temos que obter o histórico de funções com a constante 'a' naquele determinado valor buscado. O mesmo processo
ocorrerá para as demais constantes. Deve ser possível o registro do histórico ser aceito como entrada de dados para a **F003**, assim atendemos **CAP003**.
* Definimos que esta feature é um requisito **funcional**.
...

### Feature F005: Implantação da Solução de Software na AWS

* Capabilities atendidas: **CAP004**
* Descrição: ...
* Observação: definimos que esta feature é um requisito **funcional**

#### Histórias de Usuário (DevOps)

* EXEMPLO 1

```gherkin
Scenario: dada uma aplicacao em Java 11 com uso do Maven, desejamos criar um container Docker
que empacote numa imagem o servidor web na porta 8080 iniciando a aplicacao em Spring Boot.

  Given aplicacao Java com Maven
  When for solicitado um `docker build -t bhaskara-service/bhaskara-back`
  Then gere a imagem da aplicacao e publique no AWS ECR
```

* EXEMPLO 2

```gherkin
Scenario: dada a imagem do Docker da aplicacao no ECR, publique uma Stack contendo
a aplicacao de ECS Fargate subindo o nosso servidor web na porta 8080.

  Given dada a imagem no ECR e uma especificacao no Cloudformation contendo Cluster ECS Fargate
  When for acionado a implantacao
  Then aplicacao é implantada e publicada globalmente na ECS Fargate
```

### Feature F006: BhaskaraApp - um app como facilitador da educação

* Capabilities atendidas: **CAP001**, **CAP002** e **CAP003**
* Descrição: ...
* Observação: definimos que esta feature é um requisito **funcional**

> Nos parece que alguma feature tem ligação com a F006. Qual seria?
A feature que mais se conecta com a F006 é a F002. Existem outras que também se relacionaria.
Mas, no modelo Agile, precisamos garantir entregas menores e mais independentes possíveis.
Logo, quando a F002 for desenvolvida, a equipe precisa indicar a interface de Mock que o App
vai utilizar.
> Existe alguma maneira técnica de se fazer F006 sem ter que esperar por alguém fazer outra feature?
Existe sim, utilizando Mocks e Stubs.
...

## Gerenciamento de Projeto

Nosso gerente de projeto vai acompanhar o desenvolvimento e aplicar as metodologias Agile e o processo Scrum, onde temos a Scrum Daily Meeting por enquanto.
Ele observa que podemos ponderar a possibilidade de executar as features em paralelo.

As features irão compor o **Product Backlog** e as **Histórias de Usuário** irão compor a **Sprint Backlog**.

### Plano de Execução das Features

...


