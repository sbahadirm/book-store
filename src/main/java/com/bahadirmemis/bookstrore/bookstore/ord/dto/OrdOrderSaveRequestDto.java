package com.bahadirmemis.bookstrore.bookstore.ord.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class OrdOrderSaveRequestDto {

    @NotNull
    @Positive
    private Long prdBookId;

    @NotNull
    @Positive
    private Long amount;
}
