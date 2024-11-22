package com.qilicoh.boot.exception.auth;

import com.qilicoh.boot.common.enums.ResultCodeEnum;
import com.qilicoh.boot.exception.BusinessException;

public class UsernameNotFountException extends BusinessException {
    public UsernameNotFountException() {
        super(ResultCodeEnum.USERNAME_NOT_FOUNT);
    }
}
