package com.example.order.common.exception;

import com.example.order.common.response.ErrorCode;

public class IllegalStateException extends BaseException {
    public IllegalStateException() {
        super(ErrorCode.COMMON_ILLEGAL_STATUS);
    }

    public IllegalStateException(String message) {
        super(message, ErrorCode.COMMON_ILLEGAL_STATUS);
    }
}
