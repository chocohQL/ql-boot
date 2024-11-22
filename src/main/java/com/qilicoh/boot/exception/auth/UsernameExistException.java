package com.qilicoh.boot.exception.auth;

import com.qilicoh.boot.common.enums.ResultCodeEnum;
import com.qilicoh.boot.exception.BusinessException;

public class UsernameExistException extends BusinessException {
    public UsernameExistException() {
        super(ResultCodeEnum.USERNAME_EXIST);
    }
}
