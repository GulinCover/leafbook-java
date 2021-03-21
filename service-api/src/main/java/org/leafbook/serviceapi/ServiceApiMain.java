package org.leafbook.serviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ServiceApiMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApiMain.class, args);
    }
}
