package com.bahadirmemis.bookstrore.bookstore.prd.enums;

import com.bahadirmemis.bookstrore.bookstore.gen.exceptions.BaseErrorMessageType;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public enum PrdErrorMessageType implements BaseErrorMessageType {

    MOVEMENT_TYPE_CAN_NOT_BE_NULL("movement.type.cannot.be.null", "Movement type can not be null!"),
    AMOUNT_CAN_NOT_BE_ZERO_OR_NEGATIVE("amount.cannot.be.zero.or.negative", "Amount can not be zero or negative!"),
    STOCK_CAN_NOT_BE_NEGATIVE("stock.can.not.be.negative", "Stock can not be negative!"),
    ;

    private String errorCode;
    private String message;

    PrdErrorMessageType(String errorCode, String message) {
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
