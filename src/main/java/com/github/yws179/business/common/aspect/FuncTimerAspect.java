package com.github.yws179.business.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 函数计时器处理
 * @author yws
 * @date 2019/11/11
 */
@Slf4j
@Aspect
@Component
public class FuncTimerAspect {

    @Pointcut("@annotation(com.github.yws179.business.common.annotation.FuncTimer)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object timer(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object o = joinPoint.proceed();
        log.info("{} ==> takes {} ms", joinPoint.getSignature(), System.currentTimeMillis() - startTime);
        return o;
    }

}
