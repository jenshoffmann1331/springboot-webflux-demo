package com.me.springbootwebfluxdemo

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class MonoFluxTest {
    @Test
    fun `test mono`() {
        // Publisher
        val mono = Mono.just("from mono!")
            .then(Mono.error<java.lang.RuntimeException>(RuntimeException("Some Error")))
            .log()

        // Subscriber
        mono.subscribe(System.out::println)
    }

    @Test
    fun `test flux`() {
        val flux = Flux.just("Spring", "Spring Boot", "Hibernate", "Microservices")
            .concatWithValues("AWS")
            .concatWith(
                Flux.error(java.lang.RuntimeException("Something wrong"))
            )
            .concatWithValues("Cloud")
            .log()
        flux.subscribe(System.out::println)
    }
}
