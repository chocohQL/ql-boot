package com.qilicoh.boot.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import com.qilicoh.boot.common.enums.ResultCodeEnum;
import com.qilicoh.boot.domain.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomHandlerController {
    @ExceptionHandler(GlobalException.class)
    public Response handlerGlobalException(GlobalException e) {
        return Response.error(e.getMessage());
    }

    @ExceptionHandler(NotRoleException.class)
    public Response handlerNotRoleException() {
        return new Response(ResultCodeEnum.FORBIDDEN);
    }

    @ExceptionHandler(BusinessException.class)
    public Response handlerBusinessException(BusinessException e) {
        return new Response(e.getResultCodeEnum());
    }

    @ExceptionHandler(NotLoginException.class)
    public Response handlerNotLoginException(NotLoginException nle) {
        ResultCodeEnum resultCodeEnum;
        if(nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            resultCodeEnum = ResultCodeEnum.NOT_TOKEN;
        } else if(nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            resultCodeEnum = ResultCodeEnum.INVALID_TOKEN;
        } else if(nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            resultCodeEnum = ResultCodeEnum.TOKEN_TIMEOUT;
        } else if(nle.getType().equals(NotLoginException.NO_PREFIX)) {
            resultCodeEnum = ResultCodeEnum.NO_PREFIX;
        } else {
            resultCodeEnum = ResultCodeEnum.NO_LOGIN;
        }
        return new Response(resultCodeEnum);
    }

    /**
     * 处理@RequestBody接口参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationException(MethodArgumentNotValidException e) {
        return Response.error(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 处理@RequestParam接口参数校验异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Response handleConstraintViolationException(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining());
        return Response.error(message);
    }
}
