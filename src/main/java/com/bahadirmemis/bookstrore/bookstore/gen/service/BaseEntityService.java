package com.bahadirmemis.bookstrore.bookstore.gen.service;

import com.bahadirmemis.bookstrore.bookstore.cus.entity.CusCustomer;
import com.bahadirmemis.bookstrore.bookstore.gen.entity.BaseAdditionalFields;
import com.bahadirmemis.bookstrore.bookstore.gen.entity.BaseEntity;
import com.bahadirmemis.bookstrore.bookstore.gen.enums.GenErrorMessageType;
import com.bahadirmemis.bookstrore.bookstore.gen.exceptions.GenItemNotFoundException;
import com.bahadirmemis.bookstrore.bookstore.sec.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@AllArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity,D extends JpaRepository> {

    private static final Integer DEFAULT_PAGE = 0;
    private static final Integer DEFAULT_SIZE = 10;

    private final D dao;

    private AuthenticationService authenticationService;

    /** Circular dependency */
    @Autowired
    public void setAuthenticationService(@Lazy AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public List<E> findAll(){
        return dao.findAll();
    }

    public Optional<E> findById(Long id){
        return dao.findById(id);
    }

    public E save(E e){

        setAdditionalFields(e);
        e = (E) dao.save(e);

        return e;
    }

    public void delete(E e){
        dao.delete(e);
    }

    public D getDao() {
        return dao;
    }

    public E getById(Long id) {
        E entity;
        Optional<E> optionalEntity = findById(id);
        if (optionalEntity.isPresent()){
            entity = optionalEntity.get();
        } else {
            log.error(GenErrorMessageType.ITEM_NOT_FOUND.toString());
            throw new GenItemNotFoundException(GenErrorMessageType.ITEM_NOT_FOUND);
        }
        return entity;
    }

    private void setAdditionalFields(E e) {

        BaseEntity entity = e;
        BaseAdditionalFields additionalFields = e.getAdditionalFields();

        Long cusCustomerId = getLoggedUserId();

        if (additionalFields == null){
            additionalFields = new BaseAdditionalFields();
            entity.setAdditionalFields(additionalFields);
        }

        if (entity.getId() == null){
            additionalFields.setCreatedBy(cusCustomerId);
            additionalFields.setCreatedDate(new Date());
        }

        additionalFields.setModifiedBy(cusCustomerId);
        additionalFields.setModifiedDate(new Date());

    }

    protected Integer getSize(Optional<Integer> sizeOptional) {
        Integer size = DEFAULT_SIZE;
        if (sizeOptional.isPresent()){
            size = sizeOptional.get();
        }
        return size;
    }

    protected Integer getPage(Optional<Integer> pageOptional) {
        Integer page = DEFAULT_PAGE;
        if (pageOptional.isPresent()){
            page = pageOptional.get();
        }
        return page;
    }

    protected CusCustomer getLoggedUser() {
        return authenticationService.getCurrentCusCustomer();
    }

    private Long getLoggedUserId() {
        return authenticationService.getCurrentCusCustomerId();
    }
}
