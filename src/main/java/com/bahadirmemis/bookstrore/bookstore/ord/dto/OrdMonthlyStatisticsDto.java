package com.bahadirmemis.bookstrore.bookstore.ord.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class OrdMonthlyStatisticsDto {

    private Integer orderYear;
    private Integer orderMonth;
    private Long totalOrderCount;
    private Long totalBookCount;
    private BigDecimal totalPurchasedAmount;

}
