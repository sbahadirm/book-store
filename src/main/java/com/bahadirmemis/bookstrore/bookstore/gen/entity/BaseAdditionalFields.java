package com.bahadirmemis.bookstrore.bookstore.gen.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Embeddable
@Data
public class BaseAdditionalFields {

    private static final long serialVersionUID = 1L;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private Date modifiedDate;

    @Column(name = "created_by")
    @CreatedBy
    private Long createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    private Long modifiedBy;

//    @Column(length = 50)
//    private String ipAddress;
}
