# study-api

Projeto focado no estudo do CI/CD em projetos, utilizando Docker e Azure

Link: https://rm87056-checkpoint1.azurewebsites.net/

Link Swagger: https://rm87056-checkpoint1.azurewebsites.net/swagger-ui/index.html

## Processo Realizado

#### 1º - Criação de Projeto Java

Link de ajuda: https://start.spring.io/

#### 2º - Configuração de Dockerfile no Projeto

O Dockerfile, em projetos java, costuma ser padrão,

```Dockerfile
FROM maven:3.8.3-jdk-11-slim AS build

RUN mkdir /project

COPY . /project

WORKDIR /project

RUN mvn clean package

FROM adoptopenjdk/openjdk11:jre-11.0.15_10-alpine

RUN mkdir /app

COPY --from=build /project/target/app.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD java $JAVA_OPTS -jar app.jar
```

E não esqueça de configurar o pom.xml para quando buildar o projeto, criar o app.jar

```pom.xml
<build>
		<finalName>app</finalName>
<build>
```

#### 3º - Criação de reposotório no GitHub

<img src="https://media.discordapp.net/attachments/1015125358334451783/1155158571118506154/image.png?width=591&height=586" />

#### 4º - Subir projeto no reposotório do GitHub

Nessa etapa você pode subir de diversas maneiras, eu escolhi utilizar o GitHub Desktop por praticidade e costume.

[GitHub Desktop](https://desktop.github.com/)

#### 5º - Configurando Docker no PC

Instalar [Docker Desktop](https://docs.docker.com/desktop/install/windows-install)

Fazer login com sua conta docker, e criar repositório no Docker:

<img src="https://cdn.discordapp.com/attachments/1155171447673729034/1155178107116986539/image.png">

#### 6º - Enviado sua imagem para o DockerHub

Após a criação do seu repositório vamos para a linha de comando dentro do seu projeto com o Dockerfile configurado:

```bash
docker build -t study .
docker tag study dijounas/study
docker push dijounas/study
```
Explicando as variaveis:

```bash
docker build -t <nome que você quer dar a sua imagem local> .
docker tag <o nome da sua imagem local> <nome do seu repositorio no DockerHub>
docker push <nome do seu repositorio no DockerHub>
```

#### 7º - Configuração de Actions para Docker

Quando entrar no Actions do seu reposotório pela primeira vez, deve ter uma tela parecida com essa:

<img src="https://media.discordapp.net/attachments/1155171447673729034/1155182406865784893/image.png?width=1327&height=662">

Selecione o Docker image e clique em Configure e irá aparecer essa tela:

<img src="https://media.discordapp.net/attachments/1155171447673729034/1155184861494128770/image.png?width=1380&height=662">

E vamos alterar esse arquivo para:

```yml
name: Docker Image CI

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:

  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3
    - name: docker login
      run: docker login -u dijounas -p dckr_pat_JE6PlWdbTxneG-n8BomWrcAjE38
    - name: build image
      run: docker build -t dijounas/study .
    - name: push to docker
      run: docker push dijounas/study
```
Explicando as variaveis:

```yml
name: Docker Image CI

on:
  push:
    branches: [ <nome da branch> ]
  pull_request:
    branches: [ <nome da branch> ]

jobs:

  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3
    - name: docker login
      run: docker login -u <usuario> -p <token docker hub>
    - name: build image
      run: docker build -t <nome repositorio dockerhub> .
    - name: push to docker
      run: docker push <nome repositorio dockerhub>
```
Com isso, temos o deplay automatico para o DockerHub :)

#### 8.1º - Configuração de Actions para Azure

Primeiro vamos criar um Grupo de Recursos:
<img src="https://media.discordapp.net/attachments/1015125358334451783/1155194110538428486/image.png?width=1220&height=471">
<img src="https://cdn.discordapp.com/attachments/1015125358334451783/1155194558481702942/image.png">

Depois vamos criar uma Aplicação Web:

<img src="https://media.discordapp.net/attachments/1015125358334451783/1155195176512393346/image.png?width=615&height=277">

Selecione o Aplicativo Web:

<img src="https://media.discordapp.net/attachments/1015125358334451783/1155195436341141535/image.png?width=1440&height=212">

Nesse formulario pode copiar minhas configurações baseado no que você criou:

<img src="https://cdn.discordapp.com/attachments/1015125358334451783/1155195807759343726/image.png">
<img src="https://cdn.discordapp.com/attachments/1015125358334451783/1155195926655275128/image.png">

Depois clicke em Avançar: Docker, e deve aparecer essa tela e pode copiar as configurações do print abaixo:
<img src="https://media.discordapp.net/attachments/1015125358334451783/1155196211645661234/image.png?width=855&height=662">

Clicke em Revisar e Criar, e depois em Criar.

Espere a criação do recurso e depois click em Ir para o recurso.

#### 8.2º - Configuração de Actions para Azure

Após todo o processo anterior vamos procurar no menu a esquerda a opção Centro de Implantação:
<img src="https://media.discordapp.net/attachments/1015125358334451783/1155197090000011324/image.png?width=242&height=662">

E vai abrir essa tela:
<img src="https://cdn.discordapp.com/attachments/1015125358334451783/1155199512034418790/image.png">

Selecione o source do github e vincule com sua conta github:
<img src="https://media.discordapp.net/attachments/1015125358334451783/1155199988385710210/image.png">

Na proxima seção (GitHub Actions) coloque as informações do repositorio do seu projeto:
<img src="https://media.discordapp.net/attachments/1015125358334451783/1155200175426510959/image.png">

Na proxima seção (Registry Settings), vamos colocar as configurações do DockerHub (O resgistry Source deve ser Docker Hub):

<img src="https://cdn.discordapp.com/attachments/1015125358334451783/1155202325086998601/doc.png">

Agora apenas apertar no botão Save na parte de cima da tela.

#### 9º - Final
Pronto, finalizado, agora só esperar deploy e testar sua aplicação baseado no seu projeto.

#### 10º - Extra em caso de erro
Provavelmente é um erro de porta, pois ele pode não reconhecer o padrão 8080, iremos resolver ele agora

Procure a area de configuração do seu projeto no Azure:

<img src="https://media.discordapp.net/attachments/1015125358334451783/1155203137754374296/image.png">

Logo quando carregar vamos ver essa lista: 
<img src="https://media.discordapp.net/attachments/1015125358334451783/1155203606555938857/image.png?width=1200&height=468">

E vamos apertar o botão New application setting e colocar esses valores:
<img src="https://media.discordapp.net/attachments/1015125358334451783/1155203866703429702/image.png?width=866&height=554">

E então agora é só apertar ok e fazer algum commit no github para de certeza reiniciar o servidor, o problema deve ser resolvido com isso :)


## Autores

- [@Matheus22003](https://github.com/Matheus22003)

## 🔗 Links

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/matheus-sim%C3%B5es-812139189/)

