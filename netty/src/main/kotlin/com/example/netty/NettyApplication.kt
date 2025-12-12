package com.example.netty

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.modulith.Modulith
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers
import java.time.Instant
import java.util.concurrent.Executors

@SpringBootApplication
@Modulith
class NettyApplication {
    @Bean
    fun virtualThreadScheduler(): Scheduler =
        Schedulers.fromExecutorService(Executors.newVirtualThreadPerTaskExecutor())

    @Bean
    fun nettyRoutes(scheduler: Scheduler) = coRouter {
        GET("/netty/ping") {
            val payload = withContext(scheduler.asCoroutineDispatcher()) {
                mapOf(
                    "message" to "Hello from Netty WebFlux",
                    "timestamp" to Instant.now().toString(),
                    "thread" to Thread.currentThread().name
                )
            }
            ServerResponse.ok().bodyValueAndAwait(payload)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<NettyApplication>(*args)
}
