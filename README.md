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
diferentes valores de entrada, o que facilita ao professor explicar detalhes matemáticos;

* **OBJ2**: Fazer os alunos notarem como os procedimentos de encontrar raízes pode ser fácil de executar
seguindo exemplos variados;

* **OBJ3**: Explicar aos alunos as propriedades matemáticas da função. 

## Capabilities

Após intensas reuniões, Estevão expressou quais capacidades a solução de software pode oferecer.

* **CAP001** - Fazer o cálculo de raízes de uma função do segundo grau: esperamos alcançar **OBJ1**;
* **CAP002** - Mostrar um passo-a-passo da execução da Fórmula de Bhaskara: esperamos alcançar **OBJ2**;
* **CAP003** - Gerar um documento que detalhe as propriedades matemáticas da função: esperamos alcançar **OBJ3**.

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

...

### Feature F002: MathReport - um analisador matemático que gera um arquivo descrevendo o passo-a-passo do encontro de raízes e as propriedades da Equação do Segundo Grau 

* Capability atendida: **CAP002**
* Descrição: ...

...

### Feature F003 : Histórico de Expressões Matemáticas

* Capabilites atendidas: **CAP002** e **CAP003**
* Descrição: Estevão deseja buscar numa base de dados as operações já realizadas filtrando-se pelas constantes 'a', 'b' e 'c' já inseridas anteriormente.
Ele disse que se filtrarmos por 'a', temos que obter o histórico de funções com a constante 'a' naquele determinado valor buscado. O mesmo processo
ocorrerá para as demais constantes. Deve ser possível o registro do histórico ser aceito como entrada de dados para a **F002**, assim atendemos **CAP003**.

...
