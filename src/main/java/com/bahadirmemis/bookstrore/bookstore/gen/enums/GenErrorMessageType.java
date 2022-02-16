package com.bahadirmemis.bookstrore.bookstore.gen.enums;

import com.bahadirmemis.bookstrore.bookstore.gen.exceptions.BaseErrorMessageType;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public enum GenErrorMessageType implements BaseErrorMessageType {

    ITEM_NOT_FOUND("item.not.found", "Item not found!"),
    UNKNOWN_ERROR("unknown.error", "An unknown error occurred"),
    VALIDATION_FAILED("validation.failed", "Validation failed!")
    ;

    private String errorCode;
    private String message;

    GenErrorMessageType(String errorCode, String message) {
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
        return errorCode + " - " + message;
    }
}
