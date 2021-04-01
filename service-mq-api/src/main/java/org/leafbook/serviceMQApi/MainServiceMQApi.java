package org.leafbook.serviceMQApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MainServiceMQApi {
    public static void main(String[] args) {
        SpringApplication.run(MainServiceMQApi.class,args);
    }
}
