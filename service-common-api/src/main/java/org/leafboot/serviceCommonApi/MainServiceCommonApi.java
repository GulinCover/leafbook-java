package org.leafboot.serviceCommonApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class MainServiceCommonApi {
    public static void main(String[] args) {
        SpringApplication.run(MainServiceCommonApi.class, args);
    }
}
