package com.bahadirmemis.bookstrore.bookstore.ord.dao;

import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdMonthlyStatisticsDto;
import com.bahadirmemis.bookstrore.bookstore.ord.entity.OrdOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Repository
public interface OrdOrderDao extends JpaRepository<OrdOrder, Long> {

    List<OrdOrder> findAllByCusCustomerIdOrderByIdDesc(Long cusCustomerId);

    Page<OrdOrder> findAllByCusCustomerIdOrderByIdDesc(Long cusCustomerId, Pageable pageable);

    List<OrdOrder> findAllByCusCustomerIdAndOrderDateBetweenOrderByIdDesc(Long cusCustomerId, Date startDate, Date endDate);

    /**
     * SELECT
     * 	to_char(order_date, 'YYYY') as year,
     * 	to_char(order_date, 'MM') as month,
     * 	count(*) as totalOrderCount,
     * 	sum(amount) as totalBookCount,
     * 	sum( amount * b.price ) as totalPurchasedAmount
     * FROM public.ord_order o , prd_book b
     * where o.id_prd_book = b.id
     * and o.order_date between to_date('01.01.2022','dd.mm.yyyy') and to_date('01.12.2022','dd.mm.yyyy')
     * and id_cus_customer = 1
     * group by
     * 	to_char(order_date, 'YYYY'),
     * 	to_char(order_date, 'MM')
     * ;
     */
    @Query("select " +
            " new com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdMonthlyStatisticsDto(" +
            " year(ordOrder.orderDate) , " +
            " month(ordOrder.orderDate)  , " +
            " count(ordOrder.id) ," +
            " sum(ordOrder.amount) ," +
            " sum( ordOrder.amount * prdBook.price ) " +
            " ) " +
            " from OrdOrder ordOrder " +
            " left join PrdBook prdBook on  ordOrder.prdBookId = prdBook.id " +
            " where ordOrder.cusCustomerId = :cusCustomerId " +
            " and ordOrder.orderDate between :startDate and :endDate " +
            " group by year(ordOrder.orderDate), month(ordOrder.orderDate)")
    List<OrdMonthlyStatisticsDto> findAllOrdMonthlyStatisticsDtoList(
            @Param("cusCustomerId") Long cusCustomerId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}
