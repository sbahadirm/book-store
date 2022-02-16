package com.bahadirmemis.bookstrore.bookstore.gen.enums;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
class GenEnumMonthTest {

    @Test
    void valueOf_whenNumericValueIsValid() {

        GenEnumMonth genEnumMonth = GenEnumMonth.valueOf(1);

        assertEquals(genEnumMonth, GenEnumMonth.JANUARY);
    }

    @Test
    void valueOf_whenNumericValueIsInvalid() {
        assertThrows(NoSuchElementException.class,() -> GenEnumMonth.valueOf(13));
    }

}