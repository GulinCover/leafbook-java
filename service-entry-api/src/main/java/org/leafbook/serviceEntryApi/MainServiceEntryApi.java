package org.leafbook.serviceEntryApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class MainServiceEntryApi {
    public static void main(String[] args) {
        SpringApplication.run(MainServiceEntryApi.class, args);
    }
}
