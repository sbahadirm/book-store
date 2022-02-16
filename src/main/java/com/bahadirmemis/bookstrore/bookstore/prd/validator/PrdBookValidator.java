package com.bahadirmemis.bookstrore.bookstore.prd.validator;

import com.bahadirmemis.bookstrore.bookstore.gen.exceptions.GenBusinessException;
import com.bahadirmemis.bookstrore.bookstore.prd.enums.PrdErrorMessageType;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class PrdBookValidator {

    public void validateAmount(Long amount) {
        if (amount.compareTo(0L) <= 0){
            throw new GenBusinessException(PrdErrorMessageType.AMOUNT_CAN_NOT_BE_ZERO_OR_NEGATIVE);
        }
    }

    public void validateNewStock(Long newStock) {

        if (newStock.compareTo(0L) < 0){
            throw new GenBusinessException(PrdErrorMessageType.STOCK_CAN_NOT_BE_NEGATIVE);
        }
    }
}
