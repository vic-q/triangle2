package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

/**
 * @author wangqing
 */
@Aspect
@Order(1)
public class ControllerRequestAspect {

    private Logger logger = LoggerFactory.getLogger(ControllerRequestAspect.class);

    private static ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    /**
     * controller request log aspect
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {

        Optional<Method> maybeMethod = getTargetMethod(joinPoint);

        if (maybeMethod.isPresent()) {

            Method method = maybeMethod.get();

            String className = joinPoint.getTarget().getClass().getName();

            String methodName = joinPoint.getSignature().getName();

            String[] argumentNameArray = parameterNameDiscoverer.getParameterNames(method);

            Object[] argumentValueArray = joinPoint.getArgs();

            printPrettyFormatLog(className, methodName, argumentNameArray, argumentValueArray);
        }

        return joinPoint.proceed();
    }

    private Optional<Method> getTargetMethod(ProceedingJoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();

        if (Objects.nonNull(signature) && signature instanceof MethodSignature) {

            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

            Object target = joinPoint.getTarget();

            try {

                Method method = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

                return Optional.ofNullable(method);

            } catch (NoSuchMethodException e) {

                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    private void printPrettyFormatLog(String className, String methodName, String[] argumentNameArray, Object[] argumentValueArray) {

        try {
            // 构造模板

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("\n==========================================================================\n");

            stringBuilder.append("[ClassName]: ").append(className).append("\n");

            stringBuilder.append("[MethodName]: ").append(methodName).append("\n");

            stringBuilder.append("--------------------------------------------------------------------------\n");

            int limit = Math.min(argumentNameArray.length, argumentValueArray.length);

            for (int i = 0; i < limit; ++i) {

                stringBuilder.append("[").append(argumentNameArray[i]).append("]: ").append(argumentValueArray[i]).append("\n");
            }

            stringBuilder.append("==========================================================================\n");

            // 输出

            logger.info(stringBuilder.toString());

        } catch (Exception e) {

            logger.error("", e);
        }
    }
}
