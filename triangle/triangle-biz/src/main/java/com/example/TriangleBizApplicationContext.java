package com.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author wangqing 
 */
@Configuration
@ComponentScan
@Import(value = {TriangleRepositoryApplicationContext.class})
public class TriangleBizApplicationContext {

}
