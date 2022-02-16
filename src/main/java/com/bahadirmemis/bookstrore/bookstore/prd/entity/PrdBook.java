package com.bahadirmemis.bookstrore.bookstore.prd.entity;

import com.bahadirmemis.bookstrore.bookstore.gen.entity.BaseEntity;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "PRD_BOOK")
@Audited
public class PrdBook extends BaseEntity {

    @Id
    @SequenceGenerator(name = "prdbook", sequenceName = "PRD_BOOK_ID_SEQ")
    @GeneratedValue(generator = "prdbook")
    private Long id;

    @Version
    private Long version;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "WRITER_NAME", nullable = false, length = 100)
    private String writerName;

    @Column(name = "PRICE", precision = 15, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "CURRENT_STOCK")
    private Long currentStock;

}
