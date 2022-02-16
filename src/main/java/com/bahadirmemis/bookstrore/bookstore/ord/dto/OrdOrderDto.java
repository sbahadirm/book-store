package com.bahadirmemis.bookstrore.bookstore.ord.dto;

import com.bahadirmemis.bookstrore.bookstore.ord.enums.EnumOrderStatus;
import lombok.Data;

import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class OrdOrderDto {

    private Long id;
    private Long cusCustomerId;
    private Long prdBookId;
    private Date orderDate;
    private Long amount;
    private EnumOrderStatus orderStatus;
}
