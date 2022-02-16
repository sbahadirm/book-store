package com.bahadirmemis.bookstrore.bookstore.gen.entity;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@MappedSuperclass
@Data
public abstract class BaseEntity implements BaseModel, Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    @Embedded
    @Audited
    private BaseAdditionalFields additionalFields;

}
