package com.bahadirmemis.bookstrore.bookstore.gen.enums;

import java.util.Arrays;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public enum GenEnumMonth {

    JANUARY("January ", "Jan", 1),
    FEBRUARY("February ","Feb", 2),
    MARCH("March ","Mar", 3),
    APRIL("April ","Apr", 4),
    MAY("May","May", 5),
    JUNE("June","June", 6),
    JULY("July","July", 7),
    AUGUST("August ","Aug", 8),
    SEPTEMBER("September ","Sept", 9),
    OCTOBER("October ","Oct", 10),
    NOVEMBER("November ","Nov", 11),
    DECEMBER("December ","Dec", 12)
    ;

    private String fullName;
    private String shortName;
    private int numericValue;

    public static GenEnumMonth valueOf(int numericValue){

        GenEnumMonth genEnumMonth = Arrays.stream(GenEnumMonth.values())
                .filter(enumMonth -> enumMonth.getNumericValue() == numericValue)
                .findFirst()
                .orElseThrow();

        return genEnumMonth;
    }

    GenEnumMonth(String fullName, String shortName, int numericValue) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.numericValue = numericValue;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public int getNumericValue() {
        return numericValue;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
