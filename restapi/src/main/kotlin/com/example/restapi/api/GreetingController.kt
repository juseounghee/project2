package com.example.restapi.api

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/api/greeting")
class GreetingController {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun greeting(): Map<String, Any> {
        logger.info("Serving greeting on virtual thread: {}", Thread.currentThread().name)
        return mapOf(
            "message" to "Hello from REST API",
            "timestamp" to Instant.now().toString(),
            "thread" to Thread.currentThread().name
        )
    }
}
