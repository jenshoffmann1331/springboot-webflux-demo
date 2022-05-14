package com.me.springbootwebfluxdemo.handler

import com.me.springbootwebfluxdemo.dao.CustomerDao
import com.me.springbootwebfluxdemo.dto.Customer
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Service
class CustomerStreamHandler(
    private val dao: CustomerDao,
) {

    fun getCustomers(req: ServerRequest): Mono<ServerResponse> {
        val customers = dao.getCustomersStream()
        return ServerResponse
            .ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .body(customers, Customer::class.java)
    }
}
