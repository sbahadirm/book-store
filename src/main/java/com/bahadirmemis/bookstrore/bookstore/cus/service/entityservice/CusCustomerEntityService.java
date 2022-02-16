package com.bahadirmemis.bookstrore.bookstore.cus.service.entityservice;

import com.bahadirmemis.bookstrore.bookstore.cus.dao.CusCustomerDao;
import com.bahadirmemis.bookstrore.bookstore.cus.entity.CusCustomer;
import com.bahadirmemis.bookstrore.bookstore.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class CusCustomerEntityService extends BaseEntityService<CusCustomer, CusCustomerDao> {

    public CusCustomerEntityService(CusCustomerDao dao) {
        super(dao);
    }

    public CusCustomer findByUsername(String username) {
        return getDao().findByUsername(username);
    }
}
