package com.me.springbootwebfluxdemo.handler

import com.me.springbootwebfluxdemo.dao.CustomerDao
import com.me.springbootwebfluxdemo.dto.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Service
class CustomerHandler(
    @Autowired private val dao: CustomerDao,
) {

    fun loadCustomers(req: ServerRequest): Mono<ServerResponse> {
        val customerList = dao.getCustomerList()
        return ServerResponse.ok().body(customerList, Customer::class.java)
    }

    fun findCustomer(req: ServerRequest): Mono<ServerResponse> {
        var customerId = req.pathVariable("input").toInt()
        var customerMono = dao.getCustomerList()
            .filter { c -> c.id == customerId }
            .next()
        return ServerResponse.ok().body(customerMono, Customer::class.java)
    }

    fun saveCustomer(req: ServerRequest): Mono<ServerResponse> {
        var customerMono = req.bodyToMono(Customer::class.java)
        var saveResponse = customerMono.map { dto -> "${dto.id}:${dto.name}" }
        return ServerResponse.ok().body(saveResponse, String::class.java)
    }
}
