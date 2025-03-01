package com.pada.medmaster

import org.springframework.boot.SpringApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer

@TestConfiguration(proxyBeanMethods = false)
internal class TestMedmasterApplication {

    @Bean
    @ServiceConnection
    fun postgresContainer(): PostgreSQLContainer<*> {
        return PostgreSQLContainer("postgres:latest")
    }
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.from { MedMasterApplication() }.with(TestMedmasterApplication::class.java).run(*args)
        }
    }
}
