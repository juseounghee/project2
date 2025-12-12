package com.example.restapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.task.TaskExecutor
import org.springframework.modulith.Modulith
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor
import java.util.concurrent.Executors

@SpringBootApplication
@Modulith
class RestApiApplication {
    @Bean
    fun virtualThreadExecutor(): TaskExecutor =
        ConcurrentTaskExecutor(Executors.newVirtualThreadPerTaskExecutor())
}

fun main(args: Array<String>) {
    runApplication<RestApiApplication>(*args)
}
