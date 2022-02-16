package com.bahadirmemis.bookstrore.bookstore.sec.service;

import com.bahadirmemis.bookstrore.bookstore.cus.entity.CusCustomer;
import com.bahadirmemis.bookstrore.bookstore.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.bookstrore.bookstore.sec.dto.SecLoginRequestDto;
import com.bahadirmemis.bookstrore.bookstore.sec.jwt.security.EnumJwtConstant;
import com.bahadirmemis.bookstrore.bookstore.sec.jwt.security.JwtTokenGenerator;
import com.bahadirmemis.bookstrore.bookstore.sec.jwt.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenGenerator jwtTokenGenerator;

    private final CusCustomerEntityService cusCustomerEntityService;

    public String login(SecLoginRequestDto secLoginRequestDto){

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                (secLoginRequestDto.getUsername(), secLoginRequestDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);

        return EnumJwtConstant.BEARER.getConstant() + token;
    }

    public Long getCurrentCusCustomerId(){

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        Long cusCustomerId = null;
        if (jwtUserDetails != null){
            cusCustomerId = jwtUserDetails.getId();
        }

        return cusCustomerId;
    }

    public CusCustomer getCurrentCusCustomer(){

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        CusCustomer cusCustomer = cusCustomerEntityService.findByUsername(jwtUserDetails.getUsername());

        return cusCustomer;
    }

    public JwtUserDetails getCurrentJwtUserDetails(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails) {
            JwtUserDetails currentUser = (JwtUserDetails) authentication.getPrincipal();
            return currentUser;
        }

        return null;
    }
}
