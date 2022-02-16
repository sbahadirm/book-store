package com.bahadirmemis.bookstrore.bookstore.ord.service.entityservice;

import com.bahadirmemis.bookstrore.bookstore.gen.service.BaseEntityService;
import com.bahadirmemis.bookstrore.bookstore.ord.dao.OrdOrderDao;
import com.bahadirmemis.bookstrore.bookstore.ord.entity.OrdOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@Transactional
public class OrdOrderEntityService extends BaseEntityService<OrdOrder, OrdOrderDao> {

    public OrdOrderEntityService(OrdOrderDao dao) {
        super(dao);
    }

    public List<OrdOrder> findAll(Pageable pageable){
        return getDao().findAll(pageable).toList();
    }

    public List<OrdOrder> findAll(Optional<Integer> pageOptional, Optional<Integer> sizeOptional){

        Integer page = getPage(pageOptional);
        Integer size = getSize(sizeOptional);

        return getDao().findAll(PageRequest.of(page, size)).toList();
    }

    public List<OrdOrder> findAllByCusCustomerIdOrderByIdDesc(Long cusCustomerId, Optional<Integer> pageOptional, Optional<Integer> sizeOptional){

        Integer page = getPage(pageOptional);
        Integer size = getSize(sizeOptional);

        return getDao().findAllByCusCustomerIdOrderByIdDesc(cusCustomerId, PageRequest.of(page, size)).toList();
    }

    public List<OrdOrder> findAllByCusCustomerIdOrderByIdDesc(Long cusCustomerId){
        return getDao().findAllByCusCustomerIdOrderByIdDesc(cusCustomerId);
    }

    public List<OrdOrder> findAllByCusCustomerIdAndOrderDateBetween(Long cusCustomerId, Date startDate, Date endDate){
        return getDao().findAllByCusCustomerIdAndOrderDateBetweenOrderByIdDesc(cusCustomerId, startDate, endDate);
    }

    public List findAllOrdMonthlyStatisticsDtoList(Long cusCustomerId, Date startDate, Date endDate){
        return getDao().findAllOrdMonthlyStatisticsDtoList(cusCustomerId, startDate, endDate);
    }

}
