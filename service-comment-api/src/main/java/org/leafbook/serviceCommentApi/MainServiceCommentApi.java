package org.leafbook.serviceCommentApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class MainServiceCommentApi {
    public static void main(String[] args) {
        SpringApplication.run(MainServiceCommentApi.class, args);
    }
}
