package com.bahadirmemis.bookstrore.bookstore.prd.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class PrdBookDto {

    private Long id;
    private String name;
    private String writerName;
    private BigDecimal price;
}
