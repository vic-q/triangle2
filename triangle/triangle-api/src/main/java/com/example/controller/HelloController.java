package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangqing 
 */
@RestController
public class HelloController {

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    public String hello() {
        return "hello spring boot";
    }

}
