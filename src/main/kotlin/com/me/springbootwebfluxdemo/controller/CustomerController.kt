package com.me.springbootwebfluxdemo.controller

import com.me.springbootwebfluxdemo.dto.Customer
import com.me.springbootwebfluxdemo.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/customers")
class CustomerController(
    @Autowired private var service: CustomerService
) {
    @GetMapping
    fun getAllCustomers(): List<Customer> {
        return service.loadAllCustomers()
    }

    @GetMapping(
        "/stream",
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE],
    )

    fun getAllCustomersStream(): Flux<Customer> {
        return service.loadAllCustomersStream()
    }
}
