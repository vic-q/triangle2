package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangqing 
 */

@Configuration
@ConfigurationProperties(prefix = "jdbc.datasource")
@Getter
@Setter
public class JdbcDataSourceProperties {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

}
