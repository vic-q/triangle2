package com.example.aop;


import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangqing
 */
@Slf4j
@Aspect
@Order(2)
public class ValidateAspect {

    public ValidateAspect() {
    }

    /**
     * controller request validate aspect
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object validate(ProceedingJoinPoint joinPoint) throws Throwable {

        Optional<BindingResult> optionalBindingResult = Arrays
                .stream(joinPoint.getArgs())
                .filter((e) -> e instanceof BindingResult)
                .map(BindingResult.class::cast)
                .findFirst();

        Optional<List<String>> maybeDefaultMessageList = optionalBindingResult.map((bindingResult) -> {

            if (!bindingResult.hasErrors()) {

                return null;
            }

            List<ObjectError> objectErrorList = bindingResult.getAllErrors();

            return objectErrorList.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        });

        if (maybeDefaultMessageList.isPresent()) {

            List<String> defaultMessageList = maybeDefaultMessageList.get();

            throw new IllegalArgumentException("参数验证失败: " + StringUtils.join(defaultMessageList, ";\t"));
        }

        return joinPoint.proceed();
    }
}
