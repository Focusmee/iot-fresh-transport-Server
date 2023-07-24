package com.example.iotfreshtransportserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(DataSourceProperties.class)
public class IotFreshTransportServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotFreshTransportServerApplication.class, args);
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DataSourceProperties dataSourceProperties) {
        return new JdbcTemplate(dataSourceProperties.initializeDataSourceBuilder().build());
    }
}
