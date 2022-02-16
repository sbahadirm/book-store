package com.bahadirmemis.bookstrore.bookstore.prd.dao;

import com.bahadirmemis.bookstrore.bookstore.prd.entity.PrdBookStockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Repository
public interface PrdBookStockMovementDao extends JpaRepository<PrdBookStockMovement, Long> {

    List<PrdBookStockMovement> findAllByPrdBookId(Long prdBookId);
}
