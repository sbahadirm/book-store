package com.bahadirmemis.bookstrore.bookstore.cus.dao;

import com.bahadirmemis.bookstrore.bookstore.cus.entity.CusCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Repository
public interface CusCustomerDao  extends JpaRepository<CusCustomer, Long> {

    CusCustomer findByUsername(String username);
}
