package com.qilicoh.boot.common.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    boolean timingInfo() default true;

    boolean argsInfo() default true;

    boolean returnInfo() default true;

    String message() default "";
}
