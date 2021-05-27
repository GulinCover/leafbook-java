package org.leafbook.serviceCommonApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
public class MainServiceCommonApi {
    public static void main(String[] args) {
        SpringApplication.run(MainServiceCommonApi.class, args);
    }
}