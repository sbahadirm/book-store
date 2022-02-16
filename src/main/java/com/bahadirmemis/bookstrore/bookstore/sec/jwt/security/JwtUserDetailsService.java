package com.bahadirmemis.bookstrore.bookstore.sec.jwt.security;

import com.bahadirmemis.bookstrore.bookstore.cus.entity.CusCustomer;
import com.bahadirmemis.bookstrore.bookstore.cus.service.entityservice.CusCustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final CusCustomerEntityService cusCustomerEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CusCustomer cusCustomer = cusCustomerEntityService.findByUsername(username);

        return JwtUserDetails.create(cusCustomer);
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {

        CusCustomer cusCustomer = cusCustomerEntityService.getById(id);
        return JwtUserDetails.create(cusCustomer);

    }
}
