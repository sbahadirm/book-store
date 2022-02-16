package com.bahadirmemis.bookstrore.bookstore.cus.validator;

import com.bahadirmemis.bookstrore.bookstore.cus.entity.CusCustomer;
import com.bahadirmemis.bookstrore.bookstore.cus.enums.CusErrorMessageType;
import com.bahadirmemis.bookstrore.bookstore.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.bookstrore.bookstore.gen.exceptions.GenBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Service
public class CusCustomerValidator {

    private final CusCustomerEntityService cusCustomerEntityService;

    public void validateSaveCustomer(String username) {

        CusCustomer cusCustomer = cusCustomerEntityService.findByUsername(username);
        if (cusCustomer != null){
            throw new GenBusinessException(CusErrorMessageType.USERNAME_ALREADY_IN_USE);
        }
    }
}
