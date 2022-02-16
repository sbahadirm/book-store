package com.bahadirmemis.bookstrore.bookstore.prd.service;

import com.bahadirmemis.bookstrore.bookstore.gen.exceptions.GenBusinessException;
import com.bahadirmemis.bookstrore.bookstore.prd.converter.PrdBookMapper;
import com.bahadirmemis.bookstrore.bookstore.prd.dto.PrdBookDto;
import com.bahadirmemis.bookstrore.bookstore.prd.dto.PrdBookSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.prd.entity.PrdBook;
import com.bahadirmemis.bookstrore.bookstore.prd.entity.PrdBookStockMovement;
import com.bahadirmemis.bookstrore.bookstore.prd.enums.EnumStockMovementType;
import com.bahadirmemis.bookstrore.bookstore.prd.enums.PrdErrorMessageType;
import com.bahadirmemis.bookstrore.bookstore.prd.service.entityservice.PrdBookEntityService;
import com.bahadirmemis.bookstrore.bookstore.prd.service.entityservice.PrdBookStockMovementEntityService;
import com.bahadirmemis.bookstrore.bookstore.prd.validator.PrdBookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PrdBookService {

    private final PrdBookEntityService prdBookEntityService;
    private final PrdBookStockMovementEntityService prdBookStockMovementEntityService;
    private final PrdBookValidator prdBookValidator;

    public List<PrdBookDto> findAll(){
        List<PrdBook> prdBookList = prdBookEntityService.findAll();

        List<PrdBookDto> prdBookDtoList = PrdBookMapper.INSTANCE.convertToPrdBookDtoList(prdBookList);

        return prdBookDtoList;
    }

    public PrdBookDto save(PrdBookSaveRequestDto prdBookSaveRequestDto) {

        PrdBookMapper prdBookMapper = PrdBookMapper.INSTANCE;

        PrdBook prdBook = prdBookMapper.convertToPrdBook(prdBookSaveRequestDto);

        prdBook = prdBookEntityService.save(prdBook);

        PrdBookDto prdBookDto = prdBookMapper.convertToPrdBookDto(prdBook);

        return prdBookDto;
    }

    public void delete(Long id) {

        PrdBook prdBook = prdBookEntityService.getById(id);

        prdBookEntityService.delete(prdBook);
    }

    public void addStock(Long prdBookId, Long amount) {
        updateAndGetNewStock(prdBookId, amount, EnumStockMovementType.ADD);
    }

    public void removeStock(Long prdBookId, Long amount) {
        updateAndGetNewStock(prdBookId, amount, EnumStockMovementType.REMOVE);
    }

    public Long addStockAndGetNewStock(Long prdBookId, Long amount) {
        return updateAndGetNewStock(prdBookId, amount, EnumStockMovementType.ADD);
    }

    public Long removeStockAndGetNewStock(Long prdBookId, Long amount) {
        return updateAndGetNewStock(prdBookId, amount, EnumStockMovementType.REMOVE);
    }

    private Long updateAndGetNewStock(Long prdBookId, Long amount, EnumStockMovementType movementType) {

        prdBookValidator.validateAmount(amount);

        PrdBook prdBook = prdBookEntityService.getById(prdBookId);

        saveNewPrdBookStockMovement(prdBookId, amount, movementType);

        Long newStock = calculateStock(prdBook);

        prdBookValidator.validateNewStock(newStock);

        prdBook.setCurrentStock(newStock);
        prdBook = prdBookEntityService.save(prdBook);

        return newStock;
    }

    private void saveNewPrdBookStockMovement(Long id, Long amount, EnumStockMovementType movementType) {

        PrdBookStockMovement prdBookStockMovement = new PrdBookStockMovement();
        prdBookStockMovement.setPrdBookId(id);
        prdBookStockMovement.setAmount(amount);
        prdBookStockMovement.setMovementType(movementType);

        prdBookStockMovement = prdBookStockMovementEntityService.save(prdBookStockMovement);
    }

    public Long calculateStock(PrdBook prdBook) {

        Long prdBookId = prdBook.getId();

        return calculateStock(prdBookId);
    }

    public Long calculateStock(Long prdBookId) {

        List<PrdBookStockMovement> prdBookStockMovementList = prdBookStockMovementEntityService.findAllByPrdBookId(prdBookId);

        Long newStock = 0L;
        for (PrdBookStockMovement prdBookStockMovement : prdBookStockMovementList) {

            EnumStockMovementType movementType = prdBookStockMovement.getMovementType();
            Long amount = prdBookStockMovement.getAmount();

            if (EnumStockMovementType.ADD.equals(movementType)){
                newStock = newStock + amount;
            } else if (EnumStockMovementType.REMOVE.equals(movementType)){
                newStock = newStock - amount;
            } else {
                throw new GenBusinessException(PrdErrorMessageType.MOVEMENT_TYPE_CAN_NOT_BE_NULL);
            }
        }

        return newStock;
    }

}
