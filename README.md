# demointerceptor

This project demonstrates a Quarkus REST API with custom header validation using CDI interceptors and modular handler classes.

## Features

- REST endpoint at `/hello`
- Custom header validation via CDI interceptors
- Modular header validation handlers for different headers
- Integration and unit tests

## Technologies

- Java 17+
- Quarkus
- Maven
- JAX-RS
- Lombok
- Docker

## Getting Started

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8082/q/dev/>.

The API will be available at http://localhost:8082/hello.
Example Request
```shell script
curl -H "testHeader: correct" -H "testHeader1: correct1" http://localhost:8082/hello
```

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

Running with docker

```shell script
./mvnw clean package
```
```shell script
docker build -f src/main/docker/Dockerfile.jvm -t demo-interceptor .
```
```shell script
docker run -d --name demo-interceptor -p 8082:8082 demo-interceptor
```
## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/demointerceptor-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.