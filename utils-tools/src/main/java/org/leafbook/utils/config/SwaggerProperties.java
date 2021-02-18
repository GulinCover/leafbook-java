package org.leafbook.utils.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Data
@ConfigurationProperties(prefix = "swagger")
@Service
public class SwaggerProperties {
    private String title = "leafbook";
    private String description = "leafbook api document";
    private String version = "1.0.0";
    private String termsOfServiceUrl = "https://github.com/GulinCover/leafbook-java";
    private String basePackage = "org.leafbook.*";
    private String groupName = "default";
}
