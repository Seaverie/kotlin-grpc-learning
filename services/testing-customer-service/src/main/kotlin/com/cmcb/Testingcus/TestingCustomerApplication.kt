package com.cmcb.Testingcus

import com.cmcb.platform.observability.AdapterConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AdapterConfig::class)
class TestingCustomerApplication
fun main(args: Array<String>) {
    runApplication<TestingCustomerApplication>(*args)
}
