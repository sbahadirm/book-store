package com.bahadirmemis.bookstrore.bookstore.prd.service.entityservice;

import com.bahadirmemis.bookstrore.bookstore.gen.service.BaseEntityService;
import com.bahadirmemis.bookstrore.bookstore.prd.dao.PrdBookDao;
import com.bahadirmemis.bookstrore.bookstore.prd.entity.PrdBook;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class PrdBookEntityService extends BaseEntityService<PrdBook, PrdBookDao> {

    public PrdBookEntityService(PrdBookDao dao) {
        super(dao);
    }

    /**
     * version annotation provides optimistic lock,
     * @Lock provides pessimistic lock
     * also synchronized provides thread safety. it can be removed.
     *
     * @param prdBook
     * @return
     */
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Override
    public synchronized PrdBook save(PrdBook prdBook) {
        return super.save(prdBook);
    }
}
