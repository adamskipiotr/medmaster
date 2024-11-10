package com.pada.medmaster.infrastructure.adapters.`in`.rest

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension

// TODO: Do correct setup for integration and unit tests in Testcontainers
@AutoConfigureWebTestClient
@ExtendWith(SpringExtension::class)
abstract class BaseIT {

    @Autowired
    protected var testRestTemplate: TestRestTemplate? = null
}