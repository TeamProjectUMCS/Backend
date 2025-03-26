# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.4/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.4/maven-plugin/build-image.html)
* [Spring Integration AMQP Module Reference Guide](https://docs.spring.io/spring-integration/reference/amqp.html)
* [Spring Integration JPA Module Reference Guide](https://docs.spring.io/spring-integration/reference/jpa.html)
* [Spring Integration Test Module Reference Guide](https://docs.spring.io/spring-integration/reference/testing.html)
* [Spring Integration Security Module Reference Guide](https://docs.spring.io/spring-integration/reference/security.html)
* [Spring Integration HTTP Module Reference Guide](https://docs.spring.io/spring-integration/reference/http.html)
* [Spring Integration STOMP Module Reference Guide](https://docs.spring.io/spring-integration/reference/stomp.html)
* [Spring Integration WebSocket Module Reference Guide](https://docs.spring.io/spring-integration/reference/web-sockets.html)
* [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/3.4.4/reference/testing/testcontainers.html#testing.testcontainers)
* [Testcontainers RabbitMQ Module Reference Guide](https://java.testcontainers.org/modules/rabbitmq/)
* [Testcontainers GCloud Module Reference Guide](https://java.testcontainers.org/modules/gcloud/)
* [Testcontainers Postgres Module Reference Guide](https://java.testcontainers.org/modules/databases/postgres/)
* [Docker Compose Support](https://docs.spring.io/spring-boot/3.4.4/reference/features/dev-services.html#features.dev-services.docker-compose)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/3.4.4/specification/configuration-metadata/annotation-processor.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.4/reference/web/servlet.html)
* [Rest Repositories](https://docs.spring.io/spring-boot/3.4.4/how-to/data-access.html#howto.data-access.exposing-spring-data-repositories-as-rest)
* [Spring Session](https://docs.spring.io/spring-session/reference/)
* [Spring Security](https://docs.spring.io/spring-boot/3.4.4/reference/web/spring-security.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.4/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Flyway Migration](https://docs.spring.io/spring-boot/3.4.4/how-to/data-initialization.html#howto.data-initialization.migration-tool.flyway)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/3.4.4/reference/messaging/amqp.html)
* [WebSocket](https://docs.spring.io/spring-boot/3.4.4/reference/messaging/websockets.html)
* [Validation](https://docs.spring.io/spring-boot/3.4.4/reference/io/validation.html)
* [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/current/reference/htmlsingle/)
* [Testcontainers](https://java.testcontainers.org/)
* [Google Cloud Support](https://googlecloudplatform.github.io/spring-cloud-gcp/reference/html/index.html)
* [Google Cloud Messaging](https://googlecloudplatform.github.io/spring-cloud-gcp/reference/html/index.html#cloud-pubsub)
* [Google Cloud Storage](https://googlecloudplatform.github.io/spring-cloud-gcp/reference/html/index.html#cloud-storage)
* [Spring Integration](https://docs.spring.io/spring-boot/3.4.4/reference/messaging/spring-integration.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)
* [Using WebSocket to build an interactive web application](https://spring.io/guides/gs/messaging-stomp-websocket/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Google Cloud Samples](https://github.com/GoogleCloudPlatform/spring-cloud-gcp/tree/main/spring-cloud-gcp-samples)
* [Google Cloud Pub/Sub Sample](https://github.com/GoogleCloudPlatform/spring-cloud-gcp/tree/main/spring-cloud-gcp-samples/spring-cloud-gcp-pubsub-sample)
* [Google Cloud Storage](https://github.com/GoogleCloudPlatform/spring-cloud-gcp/tree/main/spring-cloud-gcp-samples/spring-cloud-gcp-storage-resource-sample)
* [Integrating Data](https://spring.io/guides/gs/integration/)

### Docker Compose support
This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* postgres: [`postgres:latest`](https://hub.docker.com/_/postgres)
* rabbitmq: [`rabbitmq:latest`](https://hub.docker.com/_/rabbitmq)

Please review the tags of the used images and set them to the same as you're running in production.

### Testcontainers support

This project uses [Testcontainers at development time](https://docs.spring.io/spring-boot/3.4.4/reference/features/dev-services.html#features.dev-services.testcontainers).

Testcontainers has been configured to use the following Docker images:

* [`postgres:latest`](https://hub.docker.com/_/postgres)
* [`rabbitmq:latest`](https://hub.docker.com/_/rabbitmq)

Please review the tags of the used images and set them to the same as you're running in production.

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

