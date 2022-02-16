package com.bahadirmemis.bookstrore.bookstore.prd.validator;

import com.bahadirmemis.bookstrore.bookstore.gen.exceptions.GenBusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class PrdBookValidatorTest {

    @InjectMocks
    private PrdBookValidator prdBookValidator;

    @Test
    void test_validateAmount_successful() {
        prdBookValidator.validateAmount(5L);
    }

    @Test
    void test_validateAmount_failForAmountIsZero() {
        assertThrows(GenBusinessException.class,() -> prdBookValidator.validateAmount(0L));
    }

    @Test
    void test_validateAmount_failForAmountIsNegative() {
        assertThrows(GenBusinessException.class,() -> prdBookValidator.validateAmount(-1L));
    }

    @Test
    void test_validateNewStock_successful() {
        prdBookValidator.validateNewStock(1L);
    }

    @Test
    void test_validateNewStock_successfulWithZero() {
        prdBookValidator.validateNewStock(0L);
    }

    @Test
    void test_validateNewStock_failForAmountIsNegative() {
        assertThrows(GenBusinessException.class,() -> prdBookValidator.validateNewStock(-1L));
    }
}