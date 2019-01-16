package com.example;

import com.example.config.DataSourceConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author wangqing
 */
@Configuration
@ComponentScan(basePackages = "com.example")
@Import(value = {DataSourceConfig.class})
public class TriangleRepositoryApplicationContext {

}
