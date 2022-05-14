package com.me.springbootwebfluxdemo.service

import com.me.springbootwebfluxdemo.dao.CustomerDao
import com.me.springbootwebfluxdemo.dto.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class CustomerService(
    @Autowired
    var customerDao: CustomerDao
) {
    fun loadAllCustomers(): List<Customer> {
        var start = System.currentTimeMillis()
        var customers = customerDao.getCustomers()
        var end = System.currentTimeMillis()
        println("Total execution time: ${end - start}")
        return customers
    }

    fun loadAllCustomersStream(): Flux<Customer> {
        var start = System.currentTimeMillis()
        var customers = customerDao.getCustomersStream()
        var end = System.currentTimeMillis()
        println("Total execution time: ${end - start}")
        return customers
    }
}
