package com.bahadirmemis.bookstrore.bookstore.prd.service.entityservice;

import com.bahadirmemis.bookstrore.bookstore.gen.service.BaseEntityService;
import com.bahadirmemis.bookstrore.bookstore.prd.dao.PrdBookStockMovementDao;
import com.bahadirmemis.bookstrore.bookstore.prd.entity.PrdBookStockMovement;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class PrdBookStockMovementEntityService extends BaseEntityService<PrdBookStockMovement, PrdBookStockMovementDao> {

    public PrdBookStockMovementEntityService(PrdBookStockMovementDao dao) {
        super(dao);
    }

    public List<PrdBookStockMovement> findAllByPrdBookId(Long prdBookId){
        return getDao().findAllByPrdBookId(prdBookId);
    }
}
