package com.bahadirmemis.bookstrore.bookstore.cus.entity;

import com.bahadirmemis.bookstrore.bookstore.gen.entity.BaseEntity;
import lombok.Data;
import org.hibernate.envers.Audited;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "CUS_CUSTOMER")
@Audited
public class CusCustomer extends BaseEntity {

    @Id
    @SequenceGenerator(name = "cuscustomer", sequenceName = "CUS_CUSTOMER_ID_SEQ")
    @GeneratedValue(generator = "cuscustomer")
    private Long id;

    @Version
    private Long version;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "SURNAME", nullable = false, length = 100)
    private String surname;

    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;
}
