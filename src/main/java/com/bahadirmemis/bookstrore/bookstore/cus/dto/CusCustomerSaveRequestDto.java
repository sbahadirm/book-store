package com.bahadirmemis.bookstrore.bookstore.cus.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class CusCustomerSaveRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String username;

    @NotNull
    @Size(min = 8)
    private String password;
}
