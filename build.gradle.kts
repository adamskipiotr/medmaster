plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot) apply true
    alias(libs.plugins.spring.dependency.management) apply true
    id("org.sonarqube") version "4.3.0.3225" apply true  // Apply SonarQube plugin directly
    id("jacoco")
}

group = "com.pada"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
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
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.graphql)
    implementation(libs.spring.boot.devtools)

    implementation(libs.springdoc.openapi.starter.webmvc.ui)
    implementation(libs.hibernate.validator)
    implementation(libs.flyway.core)

    compileOnly(libs.hibernate.jpamodelgen)

    runtimeOnly(libs.kotlin.reflect)
    runtimeOnly(libs.postgres.driver)
    runtimeOnly(libs.flyway.postgres)

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation(libs.test.containers.junit)
    testImplementation(libs.test.containers.postgresql)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sonarqube {
    properties {
        property("sonar.projectKey", "adamskipiotr_medmaster")
        property("sonar.organization", "adamskipiotr")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)  // Enable XML report for SonarCloud
        html.required.set(false)
        csv.required.set(false)
    }
}