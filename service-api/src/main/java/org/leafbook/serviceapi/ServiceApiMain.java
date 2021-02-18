package org.leafbook.serviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class ServiceApiMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApiMain.class, args);
    }
}
