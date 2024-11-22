package com.qilicoh.boot.aop;

import com.qilicoh.boot.common.annotation.MethodInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class MethodInfoAspect {
    private static final String INFO_TITLE = "[method-info] ";
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Around("@annotation(com.qilicoh.boot.common.annotation.MethodInfo)")
    public Object methodInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String method = INFO_TITLE + signature.getDeclaringType().getSimpleName() + "-" + signature.getName();
        MethodInfo methodInfo = signature.getMethod().getAnnotation(MethodInfo.class);

        Date start = new Date();
        Object result = joinPoint.proceed();
        Date end = new Date();

        info(method + " [start] --> " + FORMAT.format(start));
        info(method + " [end] --> " + FORMAT.format(end));
        if (methodInfo.timingInfo()) {
            TimeUnit timeUnit = methodInfo.timeUnit();
            long time = timeUnit.convert(end.getTime() - start.getTime(), TimeUnit.MILLISECONDS);
            info(method + " [timing] --> " + time + " " + timeUnit.toString().toLowerCase());
        }
        if (methodInfo.argsInfo()) {
            info(method + " [args] --> " + Arrays.toString(joinPoint.getArgs()));
        }
        if (methodInfo.returnInfo()) {
            info(method + " [return] --> " + result);
        }
        if (!methodInfo.message().isEmpty()) {
            info(method + " [message] --> " + methodInfo.message());
        }
        return result;
    }

    private void info(String msg) {
        log.info(msg);
    }
}
