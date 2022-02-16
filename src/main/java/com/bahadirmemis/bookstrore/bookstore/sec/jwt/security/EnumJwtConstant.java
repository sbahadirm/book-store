package com.bahadirmemis.bookstrore.bookstore.sec.jwt.security;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public enum EnumJwtConstant {

    BEARER("Bearer ");

    private String constant;

    EnumJwtConstant(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        return constant;
    }
}
