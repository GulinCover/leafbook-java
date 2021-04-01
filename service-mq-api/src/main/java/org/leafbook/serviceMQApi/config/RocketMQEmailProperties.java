package org.leafbook.serviceMQApi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "rocketmq.email")
public class RocketMQEmailProperties {
    private String namesrvAddr;
    private String topic = "";
    private String tag = "";
    private String defaultGroup = "consumerDefaultGroup";
    private String defaultInstance = "consumerDefault";
}
