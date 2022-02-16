package com.bahadirmemis.bookstrore.bookstore.ord.entity;

import com.bahadirmemis.bookstrore.bookstore.gen.entity.BaseEntity;
import com.bahadirmemis.bookstrore.bookstore.ord.enums.EnumOrderStatus;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "ORD_ORDER")
@Audited
public class OrdOrder extends BaseEntity {

    @Id
    @SequenceGenerator(name = "ordorder", sequenceName = "ORD_ORDER_ID_SEQ")
    @GeneratedValue(generator = "ordorder")
    private Long id;

    @Version
    private Long version;

    @Column(name = "ID_CUS_CUSTOMER", nullable = false)
    private Long cusCustomerId;

    @Column(name = "ID_PRD_BOOK", nullable = false)
    private Long prdBookId;

    @Column(name = "AMOUNT", nullable = false)
    private Long amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column(name = "ORDER_STATUS", length = 30)
    @Enumerated(EnumType.STRING)
    private EnumOrderStatus orderStatus;

}
