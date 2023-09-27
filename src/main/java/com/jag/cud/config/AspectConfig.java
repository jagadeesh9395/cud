package com.jag.cud.config;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@Configuration
public class AspectConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectConfig.class);
    private static final String REF_ID = "refId";

//    @Before("execution(* com.jag.cud..*.*(..))")
//    public void mdcPut(JoinPoint joinPoint) {
//        //to get method name
//        LOGGER.info("added mdc key before calling " + joinPoint.getSignature().getName() + "()" + "Controller" + joinPoint.getClass().getName());
//
//        //to get method args
//        Object[] methodArgs = joinPoint.getArgs();
//        for (Object oneArg : methodArgs) {
//            System.out.println("arg=" + oneArg);
//        }
//
////        MDC.put(REF_ID, UUID.randomUUID().toString().replace("-", "").substring(0, 12));
//        MDC.put(REF_ID, UUID.randomUUID().toString());
//    }
//
//    @After("execution(* com.jag.cud..*.*(..))")
//    public void mdcRemove(JoinPoint joinPoint) {
//        LOGGER.info("removed mdc key after calling " + joinPoint.getSignature().getName() + "()" + "Controller" + joinPoint.getClass().getName());
//
//        MDC.remove(REF_ID);
//    }

    @Around("execution(* com.jag.cud.controller..*(..)) || execution(* com.jag.cud.service..*(..)) || execution(* com.jag.cud.repository..*(..))")
    public Object mdcAround(ProceedingJoinPoint pjp) throws Throwable {
        MDC.put(REF_ID, UUID.randomUUID().toString());
        String packageName = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        long start = System.currentTimeMillis();
        Object output = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        if (!methodName.equals("initBinder")) {
            LOGGER.info("Complete method [" + packageName + "." + methodName + "()]; exec time (ms): " + elapsedTime);
        }
        return output;
    }
}
