package com.bahadirmemis.bookstrore.bookstore.prd.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class PrdBookSaveRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String writerName;

    @NotNull
    @Positive
    private BigDecimal price;
}
