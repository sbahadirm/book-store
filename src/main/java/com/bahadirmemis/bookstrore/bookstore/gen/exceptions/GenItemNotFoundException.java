package com.bahadirmemis.bookstrore.bookstore.gen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenItemNotFoundException extends GenBusinessException {

    public GenItemNotFoundException(BaseErrorMessageType baseErrorMessageType) {
        super(baseErrorMessageType);
    }
}
