package org.leafbook.serviceGatewayApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
public class MainServiceGatewayApi {
    public static void main(String[] args) {
        SpringApplication.run(MainServiceGatewayApi.class, args);
    }
}
