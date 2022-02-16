package com.bahadirmemis.bookstrore.bookstore.ord.service;

import com.bahadirmemis.bookstrore.bookstore.ord.converter.OrdOrderMapper;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdMonthlyStatisticsDto;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdMonthlyStatisticsResponseDto;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderDto;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.ord.entity.OrdOrder;
import com.bahadirmemis.bookstrore.bookstore.ord.enums.EnumOrderStatus;
import com.bahadirmemis.bookstrore.bookstore.ord.service.entityservice.OrdOrderEntityService;
import com.bahadirmemis.bookstrore.bookstore.prd.service.PrdBookService;
import com.bahadirmemis.bookstrore.bookstore.sec.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@Transactional
@RequiredArgsConstructor
public class OrdOrderService {

    private final OrdOrderEntityService ordOrderEntityService;
    private final PrdBookService prdBookService;
    private final AuthenticationService authenticationService;

    public List<OrdOrderDto> findAll(Optional<Integer> pageOptional, Optional<Integer> sizeOptional) {

        List<OrdOrder> ordOrderList = ordOrderEntityService.findAll(pageOptional, sizeOptional);

        List<OrdOrderDto> ordOrderDtoList = OrdOrderMapper.INSTANCE.convertToOrdOrderDtoList(ordOrderList);

        return ordOrderDtoList;
    }

    public OrdOrderDto findById(Long id) {

        OrdOrder ordOrder = ordOrderEntityService.getById(id);

        OrdOrderDto ordOrderDto = OrdOrderMapper.INSTANCE.convertToOrdOrderDto(ordOrder);

        return ordOrderDto;
    }

    public OrdOrderDto save(OrdOrderSaveRequestDto ordOrderSaveRequestDto) {

        OrdOrder ordOrder = OrdOrderMapper.INSTANCE.convertToOrdOrder(ordOrderSaveRequestDto);

        ordOrder = orderBook(ordOrder);

        OrdOrderDto ordOrderDto = OrdOrderMapper.INSTANCE.convertToOrdOrderDto(ordOrder);

        return ordOrderDto;
    }

    public void delete(Long id) {

        OrdOrder ordOrder = ordOrderEntityService.getById(id);

        ordOrderEntityService.delete(ordOrder);
    }

    public List<OrdOrderDto> getAllOrdersByCustomerId(Long customerId, Optional<Integer> pageOptional, Optional<Integer> sizeOptional) {

        List<OrdOrder> ordOrderList = ordOrderEntityService.findAllByCusCustomerIdOrderByIdDesc(customerId, pageOptional, sizeOptional);

        List<OrdOrderDto> ordOrderDtoList = OrdOrderMapper.INSTANCE.convertToOrdOrderDtoList(ordOrderList);

        return ordOrderDtoList;
    }

    public List<OrdOrderDto> findAllOrdersByGivenRange(Date startDate, Date endDate) {
        Long cusCustomerId = authenticationService.getCurrentCusCustomerId();

        List<OrdOrder> ordOrderList = ordOrderEntityService.findAllByCusCustomerIdAndOrderDateBetween
                (cusCustomerId, startDate, endDate);

        List<OrdOrderDto> ordOrderDtoList = OrdOrderMapper.INSTANCE.convertToOrdOrderDtoList(ordOrderList);

        return ordOrderDtoList;
    }

    public List<OrdMonthlyStatisticsResponseDto> getMontlyStatistics(Date startDate, Date endDate) {

        Long cusCustomerId = authenticationService.getCurrentCusCustomerId();

        List<OrdMonthlyStatisticsDto> ordMonthlyStatisticsDtoList = ordOrderEntityService
                .findAllOrdMonthlyStatisticsDtoList(cusCustomerId, startDate, endDate);

        List<OrdMonthlyStatisticsResponseDto> result =
                OrdOrderMapper.INSTANCE.convertToOrdMonthlyStatisticsResponseDtoList(ordMonthlyStatisticsDtoList);

        return result;
    }

    private OrdOrder orderBook(OrdOrder ordOrder) {

        Long cusCustomerId = authenticationService.getCurrentCusCustomerId();

        Long prdBookId = ordOrder.getPrdBookId();
        Long amount = ordOrder.getAmount();

        prdBookService.removeStock(prdBookId, amount);

        ordOrder.setCusCustomerId(cusCustomerId);
        ordOrder.setOrderDate(new Date());
        ordOrder.setOrderStatus(EnumOrderStatus.RECEIVED);
        ordOrder = ordOrderEntityService.save(ordOrder);

        return ordOrder;
    }
}
