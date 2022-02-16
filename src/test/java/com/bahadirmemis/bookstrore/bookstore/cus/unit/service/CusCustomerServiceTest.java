package com.bahadirmemis.bookstrore.bookstore.cus.unit.service;

import com.bahadirmemis.bookstrore.bookstore.cus.dto.CusCustomerDto;
import com.bahadirmemis.bookstrore.bookstore.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.cus.entity.CusCustomer;
import com.bahadirmemis.bookstrore.bookstore.cus.service.CusCustomerService;
import com.bahadirmemis.bookstrore.bookstore.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.bookstrore.bookstore.cus.validator.CusCustomerValidator;
import com.bahadirmemis.bookstrore.bookstore.gen.enums.GenErrorMessageType;
import com.bahadirmemis.bookstrore.bookstore.gen.exceptions.GenBusinessException;
import com.bahadirmemis.bookstrore.bookstore.gen.exceptions.GenItemNotFoundException;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderDto;
import com.bahadirmemis.bookstrore.bookstore.ord.service.OrdOrderService;
import com.bahadirmemis.bookstrore.bookstore.sec.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */

@ExtendWith(MockitoExtension.class)
class CusCustomerServiceTest {

    @Mock
    private CusCustomerEntityService cusCustomerEntityService;

    @Mock
    private CusCustomerValidator cusCustomerValidator;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private OrdOrderService ordOrderService;

    @InjectMocks
    private CusCustomerService cusCustomerService;

    @Test
    public void test_findAll_shouldBeSuccess(){

        CusCustomer cusCustomer = Mockito.mock(CusCustomer.class);
        List<CusCustomer> cusCustomerList = new ArrayList<>();
        cusCustomerList.add(cusCustomer);

        when(cusCustomerEntityService.findAll()).thenReturn(cusCustomerList);

        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();

        assertEquals(1, cusCustomerDtoList.size());
    }

    @Test
    public void test_Save_shouldBeSuccess(){

        CusCustomerSaveRequestDto customerSaveRequestDto = Mockito.mock(CusCustomerSaveRequestDto.class);
        when(customerSaveRequestDto.getUsername()).thenReturn("sbahadirm");
        when(customerSaveRequestDto.getPassword()).thenReturn("123");

        CusCustomer cusCustomer = Mockito.mock(CusCustomer.class);
        when(cusCustomer.getId()).thenReturn(1L);

        doNothing().when(cusCustomerValidator).validateSaveCustomer(anyString());
        when(passwordEncoder.encode(anyString())).thenReturn("678");
        when(cusCustomerEntityService.save(any())).thenReturn(cusCustomer);

        CusCustomerDto customerDto = cusCustomerService.save(customerSaveRequestDto);

        verify(cusCustomerValidator).validateSaveCustomer(anyString());

        assertEquals(customerDto.getId(), 1L);

    }

    @Test
    public void test_save_shouldNotBeSuccessBecauseCustomerAlreadyExists(){

        CusCustomerSaveRequestDto customerSaveRequestDto = Mockito.mock(CusCustomerSaveRequestDto.class);
        when(customerSaveRequestDto.getUsername()).thenReturn("sbahadirm");

        doThrow(GenBusinessException.class).when(cusCustomerValidator).validateSaveCustomer(anyString());

        assertThrows(GenBusinessException.class,() -> cusCustomerService.save(customerSaveRequestDto));
    }

    @Test
    public void test_delete_shouldBeSuccess(){

        CusCustomer cusCustomer = Mockito.mock(CusCustomer.class);

        when(cusCustomerEntityService.getById(anyLong())).thenReturn(cusCustomer);

        cusCustomerService.delete(anyLong());

        verify(cusCustomerEntityService).getById(anyLong());
        verify(cusCustomerEntityService).delete(any());
    }

    @Test
    void test_delete_shouldIdDoesNotExist() {

        when(cusCustomerEntityService.getById(anyLong()))
                .thenThrow(new GenItemNotFoundException(GenErrorMessageType.ITEM_NOT_FOUND));

        assertThrows(GenItemNotFoundException.class, () -> cusCustomerService.delete(0L));

        verify(cusCustomerEntityService).getById(anyLong());
    }

    @Test
    public void test_getAllOrdersByCurrentCustomer_shouldBeSuccess(){

        OrdOrderDto ordOrderDto = mock(OrdOrderDto.class);
        List<OrdOrderDto> ordOrderDtoList = new ArrayList<>();
        ordOrderDtoList.add(ordOrderDto);

        when(authenticationService.getCurrentCusCustomerId()).thenReturn(1L);
        when(ordOrderService.getAllOrdersByCustomerId(1L, null, null)).thenReturn(ordOrderDtoList);

        List<OrdOrderDto> result = cusCustomerService.getAllOrdersByCurrentCustomer(null, null);

        assertEquals(1, result.size());

    }
}