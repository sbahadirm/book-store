package com.bahadirmemis.bookstrore.bookstore.ord.service;

import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdMonthlyStatisticsDto;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdMonthlyStatisticsResponseDto;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderDto;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.ord.entity.OrdOrder;
import com.bahadirmemis.bookstrore.bookstore.ord.service.entityservice.OrdOrderEntityService;
import com.bahadirmemis.bookstrore.bookstore.prd.service.PrdBookService;
import com.bahadirmemis.bookstrore.bookstore.sec.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class OrdOrderServiceTest {

    @Mock
    private OrdOrderEntityService ordOrderEntityService;

    @Mock
    private PrdBookService prdBookService;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private OrdOrderService ordOrderService;

    @Test
    void test_findAll_successful() {

        OrdOrder ordOrder = Mockito.mock(OrdOrder.class);
        List<OrdOrder> ordOrderList = new ArrayList<>();
        ordOrderList.add(ordOrder);

        when(ordOrderEntityService.findAll(null, null)).thenReturn(ordOrderList);

        List<OrdOrderDto> ordOrderDtoList = ordOrderService.findAll(null, null);

        assertEquals(1, ordOrderDtoList.size());

    }

    @Test
    void test_findById_successful() {

        OrdOrder ordOrder = Mockito.mock(OrdOrder.class);
        when(ordOrder.getId()).thenReturn(1L);

        when(ordOrderEntityService.getById(anyLong())).thenReturn(ordOrder);

        OrdOrderDto ordOrderDto = ordOrderService.findById(anyLong());

        assertEquals(1L, ordOrderDto.getId());
    }

    @Test
    void test_save_successful() {

        OrdOrder ordOrder = Mockito.mock(OrdOrder.class);
        when(ordOrder.getId()).thenReturn(1L);

        OrdOrderSaveRequestDto ordOrderSaveRequestDto = Mockito.mock(OrdOrderSaveRequestDto.class);

        when(authenticationService.getCurrentCusCustomerId()).thenReturn(1L);
        doNothing().when(prdBookService).removeStock(anyLong(), anyLong());
        when(ordOrderEntityService.save(any())).thenReturn(ordOrder);

        OrdOrderDto ordOrderDto = ordOrderService.save(ordOrderSaveRequestDto);

        assertEquals(1L, ordOrderDto.getId());

    }

    @Test
    void test_delete_successful() {

        OrdOrder ordOrder = Mockito.mock(OrdOrder.class);

        when(ordOrderEntityService.getById(anyLong())).thenReturn(ordOrder);

        ordOrderService.delete(anyLong());

        verify(ordOrderEntityService).getById(anyLong());
        verify(ordOrderEntityService).delete(any());
    }

    @Test
    void test_getAllOrdersByCustomerId_successful() {

        OrdOrder ordOrder = Mockito.mock(OrdOrder.class);
        List<OrdOrder> ordOrderList = new ArrayList<>();
        ordOrderList.add(ordOrder);

        when(ordOrderEntityService.findAllByCusCustomerIdOrderByIdDesc(1L, null, null)).thenReturn(ordOrderList);

        List<OrdOrderDto> result = ordOrderService.getAllOrdersByCustomerId(1L, null, null);

        assertEquals(1, result.size());
    }

    @Test
    void test_findAllOrdersByGivenRange_successful() {

        OrdOrder ordOrder = Mockito.mock(OrdOrder.class);
        List<OrdOrder> ordOrderList = new ArrayList<>();
        ordOrderList.add(ordOrder);

        Date date = mock(Date.class);

        when(authenticationService.getCurrentCusCustomerId()).thenReturn(1L);
        when(ordOrderEntityService.findAllByCusCustomerIdAndOrderDateBetween(1L, date, date)).thenReturn(ordOrderList);

        List<OrdOrderDto> result = ordOrderService.findAllOrdersByGivenRange(date, date);

        assertEquals(1, result.size());

    }

    @Test
    void test_getMontlyStatistics_successful() {

        OrdMonthlyStatisticsDto ordMonthlyStatisticsDto = Mockito.mock(OrdMonthlyStatisticsDto.class);
        when(ordMonthlyStatisticsDto.getOrderMonth()).thenReturn(1);
        List<OrdMonthlyStatisticsDto> ordMonthlyStatisticsDtoList = new ArrayList<>();
        ordMonthlyStatisticsDtoList.add(ordMonthlyStatisticsDto);

        Date date = mock(Date.class);

        when(authenticationService.getCurrentCusCustomerId()).thenReturn(1L);
        when(ordOrderEntityService.findAllOrdMonthlyStatisticsDtoList(1L, date, date)).thenReturn(ordMonthlyStatisticsDtoList);

        List<OrdMonthlyStatisticsResponseDto> result = ordOrderService.getMontlyStatistics(date, date);

        assertEquals(1, result.size());

    }
}