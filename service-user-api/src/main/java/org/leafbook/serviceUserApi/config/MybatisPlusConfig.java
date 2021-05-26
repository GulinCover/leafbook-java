package org.leafbook.serviceUserApi.config;

import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "custom-mybatis-plus.datasource")
@Configuration
public class MybatisPlusConfig {
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }

//    @Bean
//    @Primary
//    public DataSource dataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        System.out.println(url);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setDriverClassName(driverClassName);
//        return dataSource;
//    }
}
