package com.me.springbootwebfluxdemo.router

import com.me.springbootwebfluxdemo.handler.CustomerHandler
import com.me.springbootwebfluxdemo.handler.CustomerStreamHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class RouterConfig(
    private val handler: CustomerHandler,
    private val streamHandler: CustomerStreamHandler,
) {

    @Bean
    fun routerFunction(): RouterFunction<ServerResponse> {
        return router {
            GET("/router/customers", handler::loadCustomers)
            GET("/router/customers/stream", streamHandler::getCustomers)
            GET("/router/customer/{input}", handler::findCustomer)
            POST("/router/customer/save", handler::saveCustomer)
        }
    }
}
