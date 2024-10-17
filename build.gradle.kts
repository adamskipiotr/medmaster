plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.pada"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-graphql:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.0.4")
    // https://mvnrepository.com/artifact/org.springframework/spring-websocket
    implementation("org.springframework:spring-websocket:6.0.10")
// https://mvnrepository.com/artifact/org.springframework/spring-messaging
    implementation("org.springframework:spring-messaging:6.0.10")

    implementation("com.graphql-java:graphql-java:21.5")
    // https://mvnrepository.com/artifact/com.graphql-java-kickstart/playground-spring-boot-starter
    runtimeOnly("com.graphql-java-kickstart:playground-spring-boot-starter:5.10.0")

    // https://mvnrepository.com/artifact/org.flywaydb/flyway-core
    implementation("org.flywaydb:flyway-core:9.19.3")

    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    // https://mvnrepository.com/artifact/javax.persistence/javax.persistence-api
    implementation("javax.persistence:javax.persistence-api:2.2")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation
    implementation("org.springframework.boot:spring-boot-starter-validation:3.1.0")
    // https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa
    implementation("org.springframework.data:spring-data-jpa:3.1.0")
    // https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
    implementation("org.projectlombok:lombok:1.18.26")
    runtimeOnly("org.postgresql:postgresql:42.7.2")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    // https://mvnrepository.com/artifact/org.projectlombok/lombok-mapstruct-binding
    implementation("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    // https://mvnrepository.com/artifact/org.mapstruct/mapstruct
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    // https://mvnrepository.com/artifact/org.mapstruct/mapstruct-processor
    implementation("org.mapstruct:mapstruct-processor:1.5.5.Final")

    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    //Needed for Specification<> to generate metamodel classes for entities
    annotationProcessor("org.hibernate:hibernate-jpamodelgen:6.1.7.Final")


    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.springframework:spring-webflux:6.0.6")
    testImplementation("org.springframework.graphql:spring-graphql-test:1.2.0")
    testImplementation("org.testcontainers:junit-jupiter:1.17.6")
    // https://mvnrepository.com/artifact/org.testcontainers/testcontainers
    testImplementation("org.testcontainers:testcontainers:1.18.3")
    // https://mvnrepository.com/artifact/org.springframework/spring-aop
    implementation("org.springframework:spring-aop:6.0.10")
    // https://mvnrepository.com/artifact/org.aspectj/aspectjweaver
    runtimeOnly("org.aspectj:aspectjweaver:1.9.19")
    // https://mvnrepository.com/artifact/org.testcontainers/postgresql
    testImplementation("org.testcontainers:postgresql:1.18.3")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")

}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
