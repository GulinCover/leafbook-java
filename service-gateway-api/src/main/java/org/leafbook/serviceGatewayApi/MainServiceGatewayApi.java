package org.leafbook.serviceGatewayApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MainServiceGatewayApi {
    public static void main(String[] args) {
        SpringApplication.run(MainServiceGatewayApi.class, args);
    }
}
