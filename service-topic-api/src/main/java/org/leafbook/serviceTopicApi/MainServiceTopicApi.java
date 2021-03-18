package org.leafbook.serviceTopicApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class MainServiceTopicApi {
    public static void main(String[] args) {
        SpringApplication.run(MainServiceTopicApi.class, args);
    }
}
