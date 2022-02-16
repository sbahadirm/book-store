package com.bahadirmemis.bookstrore.bookstore.cus.unit.validator;

import com.bahadirmemis.bookstrore.bookstore.cus.entity.CusCustomer;
import com.bahadirmemis.bookstrore.bookstore.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.bookstrore.bookstore.cus.validator.CusCustomerValidator;
import com.bahadirmemis.bookstrore.bookstore.gen.exceptions.GenBusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class CusCustomerValidatorTest {

    @Mock
    private CusCustomerEntityService cusCustomerEntityService;

    @InjectMocks
    private CusCustomerValidator cusCustomerValidator;

    @Test
    void validateSaveCustomer_shouldBeValid() {

        when(cusCustomerEntityService.findByUsername(anyString()))
                .thenReturn(null);

        cusCustomerValidator.validateSaveCustomer(anyString());
    }


    @Test
    void validateSaveCustomer_shouldNotBeValid() {

        CusCustomer cusCustomer = mock(CusCustomer.class);

        when(cusCustomerEntityService.findByUsername(anyString()))
                .thenReturn(cusCustomer);

        assertThrows(GenBusinessException.class,() -> cusCustomerValidator.validateSaveCustomer(anyString()));

    }
}