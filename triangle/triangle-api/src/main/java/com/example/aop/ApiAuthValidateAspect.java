package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author wangqing 
 */

@Aspect
public class ApiAuthValidateAspect {

    public Object auth(ProceedingJoinPoint joinPoint) throws Throwable {
        return  joinPoint.proceed();
    }

}
