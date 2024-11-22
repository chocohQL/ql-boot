package com.qilicoh.boot.exception.auth;

import com.qilicoh.boot.common.enums.ResultCodeEnum;
import com.qilicoh.boot.exception.BusinessException;

public class PasswordErrorException extends BusinessException {
    public PasswordErrorException() {
        super(ResultCodeEnum.PASSWORD_ERROR);
    }
}
