package com.qilicoh.boot.exception;

import lombok.Getter;

public class GlobalException extends RuntimeException {

    private String message;

    @Getter
    private String detailMessage;

    public GlobalException() {
    }

    public GlobalException(String message) {
        this.message = message;
    }

    public GlobalException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public GlobalException setMessage(String message) {
        this.message = message;
        return this;
    }
}
