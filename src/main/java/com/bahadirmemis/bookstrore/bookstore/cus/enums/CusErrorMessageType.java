package com.bahadirmemis.bookstrore.bookstore.cus.enums;

import com.bahadirmemis.bookstrore.bookstore.gen.exceptions.BaseErrorMessageType;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public enum CusErrorMessageType implements BaseErrorMessageType {

    USERNAME_ALREADY_IN_USE("username.already.in.use", "Username already in use"),

    ;

    private String errorCode;
    private String message;

    CusErrorMessageType(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return message;
    }
}
