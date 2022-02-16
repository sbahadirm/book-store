package com.bahadirmemis.bookstrore.bookstore.ord.dto;

import com.bahadirmemis.bookstrore.bookstore.gen.enums.GenEnumMonth;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class OrdMonthlyStatisticsResponseDto {

    private Integer year;
    private GenEnumMonth month;
    private Long totalOrderCount;
    private Long totalBookCount;
    private BigDecimal totalPurchasedAmount;

}
