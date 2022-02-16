package com.bahadirmemis.bookstrore.bookstore.gen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private Date errorDate;
    private String code;
    private String message;
    private String detail;

}
