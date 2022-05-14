package com.me.springbootwebfluxdemo.dao

import com.me.springbootwebfluxdemo.dto.Customer
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.stream.Collectors
import java.util.stream.IntStream

@Component
class CustomerDao {

    fun getCustomers(): List<Customer> {
        return IntStream.rangeClosed(1, 10)
            .peek { Thread.sleep(1000) }
            .peek { i -> println("processing count: " + i) }
            .mapToObj { i -> Customer(i, "customer" + i) }
            .collect(Collectors.toList())
    }

    fun getCustomersStream(): Flux<Customer> {
        return Flux.range(1, 10)
            .delayElements(Duration.ofSeconds(1))
            .doOnNext { i -> println("processing count: " + i) }
            .map { i -> Customer(i, "customer" + i) }

    }

    fun getCustomerList(): Flux<Customer> {
        return Flux.range(1, 10)
            .doOnNext { i -> println("processing count: " + i) }
            .map { i -> Customer(i, "customer" + i) }

    }
}
