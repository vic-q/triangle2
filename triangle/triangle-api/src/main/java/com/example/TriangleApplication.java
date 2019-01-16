package com.example;

import com.example.aop.ControllerRequestAspect;
import com.example.aop.ValidateAspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Configuration
@Import(value = {TriangleApplicationContext.class})
public class TriangleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TriangleApplication.class, args);
    }

    @Bean
    public ValidateAspect validateAspect() {
        return new ValidateAspect();
    }

    @Bean
    public ControllerRequestAspect controllerRequestAspect() {
        return new ControllerRequestAspect();
    }

}

