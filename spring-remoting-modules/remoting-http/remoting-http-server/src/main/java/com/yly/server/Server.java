package com.yly.server;


import com.yly.api.CabBookingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;


/**
 * exposing the service
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Server {

    /**
     * we then need to define an application with a bean of type HttpInvokerServiceExporter in the context . it will take
     * care of exposing an HTTP entry point in the web application that will be later invoked by the client
     *
     * it is worth nothing that Spring's HTTP invoker uses the name of the HttpInvokerServiceExporter bean as a relative
     * path for the HTTP endpoint URL
     */
    @Bean(name = "/booking")
    HttpInvokerServiceExporter accountService() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(new CabBookingServiceImpl());
        exporter.setServiceInterface(CabBookingService.class);
        return exporter;
    }

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

}