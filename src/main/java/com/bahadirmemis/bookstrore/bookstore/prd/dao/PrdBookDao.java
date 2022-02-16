package com.bahadirmemis.bookstrore.bookstore.prd.dao;

import com.bahadirmemis.bookstrore.bookstore.prd.entity.PrdBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Repository
public interface PrdBookDao extends JpaRepository<PrdBook, Long> {
}
