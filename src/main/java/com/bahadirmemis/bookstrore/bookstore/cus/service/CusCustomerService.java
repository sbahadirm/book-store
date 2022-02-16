package com.bahadirmemis.bookstrore.bookstore.cus.service;

import com.bahadirmemis.bookstrore.bookstore.cus.converter.CusCustomerMapper;
import com.bahadirmemis.bookstrore.bookstore.cus.dto.CusCustomerDto;
import com.bahadirmemis.bookstrore.bookstore.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.cus.entity.CusCustomer;
import com.bahadirmemis.bookstrore.bookstore.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.bookstrore.bookstore.cus.validator.CusCustomerValidator;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderDto;
import com.bahadirmemis.bookstrore.bookstore.ord.service.OrdOrderService;
import com.bahadirmemis.bookstrore.bookstore.sec.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CusCustomerService {

    private final PasswordEncoder passwordEncoder;
    private final CusCustomerEntityService cusCustomerEntityService;
    private final OrdOrderService ordOrderService;
    private final AuthenticationService authenticationService;
    private final CusCustomerValidator cusCustomerValidator;

    public List<CusCustomerDto> findAll() {

        List<CusCustomer> cusCustomerList = cusCustomerEntityService.findAll();

        List<CusCustomerDto> cusCustomerDtoList = CusCustomerMapper.INSTANCE.convertToCusCustomerDtoList(cusCustomerList);

        return cusCustomerDtoList;
    }

    public CusCustomerDto save(CusCustomerSaveRequestDto cusCustomerSaveRequestDto) {

        cusCustomerValidator.validateSaveCustomer(cusCustomerSaveRequestDto.getUsername());

        CusCustomer cusCustomer = CusCustomerMapper.INSTANCE.convertToCusCustomer(cusCustomerSaveRequestDto);

        String encodedPassword = passwordEncoder.encode(cusCustomer.getPassword());
        cusCustomer.setPassword(encodedPassword);

        cusCustomer = cusCustomerEntityService.save(cusCustomer);

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }

    public void delete(Long id) {

        CusCustomer cusCustomer = cusCustomerEntityService.getById(id);

        cusCustomerEntityService.delete(cusCustomer);
    }

    public List<OrdOrderDto> getAllOrdersByCurrentCustomer(Optional<Integer> pageOptional, Optional<Integer> sizeOptional) {

        Long cusCustomerId = authenticationService.getCurrentCusCustomerId();

        return ordOrderService.getAllOrdersByCustomerId(cusCustomerId, pageOptional, sizeOptional);
    }

}
