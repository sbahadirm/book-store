package com.bahadirmemis.bookstrore.bookstore.prd.entity;

import com.bahadirmemis.bookstrore.bookstore.gen.entity.BaseEntity;
import com.bahadirmemis.bookstrore.bookstore.prd.enums.EnumStockMovementType;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "PRD_BOOK_STOCK_MOVEMENT")
@Audited
public class PrdBookStockMovement extends BaseEntity {

    @Id
    @SequenceGenerator(name = "prdbookstockmovement", sequenceName = "PRD_BOOK_STOCK_MOVEMENT_ID_SEQ")
    @GeneratedValue(generator = "prdbookstockmovement")
    private Long id;

    @Version
    private Long version;

    @Column(name = "ID_PRD_BOOK", nullable = false)
    private Long prdBookId;

    @Column(name = "AMOUNT", nullable = false)
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "MOVEMENT_TYPE", length = 30)
    private EnumStockMovementType movementType;

}
