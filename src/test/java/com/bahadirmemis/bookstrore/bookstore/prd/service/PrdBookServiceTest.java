package com.bahadirmemis.bookstrore.bookstore.prd.service;

import com.bahadirmemis.bookstrore.bookstore.cus.dto.CusCustomerDto;
import com.bahadirmemis.bookstrore.bookstore.cus.entity.CusCustomer;
import com.bahadirmemis.bookstrore.bookstore.gen.exceptions.GenBusinessException;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderDto;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.ord.entity.OrdOrder;
import com.bahadirmemis.bookstrore.bookstore.prd.dto.PrdBookDto;
import com.bahadirmemis.bookstrore.bookstore.prd.dto.PrdBookSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.prd.entity.PrdBook;
import com.bahadirmemis.bookstrore.bookstore.prd.entity.PrdBookStockMovement;
import com.bahadirmemis.bookstrore.bookstore.prd.enums.EnumStockMovementType;
import com.bahadirmemis.bookstrore.bookstore.prd.service.entityservice.PrdBookEntityService;
import com.bahadirmemis.bookstrore.bookstore.prd.service.entityservice.PrdBookStockMovementEntityService;
import com.bahadirmemis.bookstrore.bookstore.prd.validator.PrdBookValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class PrdBookServiceTest {

    @Mock
    private PrdBookStockMovementEntityService prdBookStockMovementEntityService;

    @Mock
    private PrdBookEntityService prdBookEntityService;

    @Mock
    private PrdBookValidator prdBookValidator;

    @InjectMocks
    private PrdBookService prdBookService;

    @Test
    public void test_findAll_successful(){

        PrdBook prdBook = Mockito.mock(PrdBook.class);
        List<PrdBook> prdBookList = new ArrayList<>();
        prdBookList.add(prdBook);

        when(prdBookEntityService.findAll()).thenReturn(prdBookList);

        List<PrdBookDto> cusCustomerDtoList = prdBookService.findAll();

        assertEquals(1, cusCustomerDtoList.size());
    }

    @Test
    public void test_save_successful(){

        PrdBook prdBook = Mockito.mock(PrdBook.class);
        when(prdBook.getId()).thenReturn(1L);

        PrdBookSaveRequestDto ordOrderSaveRequestDto = Mockito.mock(PrdBookSaveRequestDto.class);

        when(prdBookEntityService.save(any())).thenReturn(prdBook);

        PrdBookDto prdBookDto = prdBookService.save(ordOrderSaveRequestDto);

        assertEquals(1L, prdBookDto.getId());
    }

    @Test
    public void test_delete_successful(){

        PrdBook prdBook = Mockito.mock(PrdBook.class);

        when(prdBookEntityService.getById(anyLong())).thenReturn(prdBook);

        prdBookService.delete(anyLong());

        verify(prdBookEntityService).getById(anyLong());
        verify(prdBookEntityService).delete(any());
    }

    @Test
    public void test_addStock_successful(){

        PrdBook prdBook = Mockito.mock(PrdBook.class);
        PrdBookStockMovement prdBookStockMovement = Mockito.mock(PrdBookStockMovement.class);

        PrdBookStockMovement prdBookStockMovement1 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement1.getMovementType()).thenReturn(EnumStockMovementType.ADD);
        when(prdBookStockMovement1.getAmount()).thenReturn(100L);

        PrdBookStockMovement prdBookStockMovement2 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement2.getMovementType()).thenReturn(EnumStockMovementType.ADD);
        when(prdBookStockMovement2.getAmount()).thenReturn(10L);

        PrdBookStockMovement prdBookStockMovement3 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement3.getMovementType()).thenReturn(EnumStockMovementType.REMOVE);
        when(prdBookStockMovement3.getAmount()).thenReturn(1L);

        List<PrdBookStockMovement> prdBookStockMovementList = new ArrayList<>();
        prdBookStockMovementList.add(prdBookStockMovement1);
        prdBookStockMovementList.add(prdBookStockMovement2);
        prdBookStockMovementList.add(prdBookStockMovement3);

        doNothing().when(prdBookValidator).validateAmount(anyLong());
        when(prdBookEntityService.getById(anyLong())).thenReturn(prdBook);
        when(prdBookStockMovementEntityService.save(any())).thenReturn(prdBookStockMovement);
        when(prdBookStockMovementEntityService.findAllByPrdBookId(anyLong())).thenReturn(prdBookStockMovementList);
        doNothing().when(prdBookValidator).validateNewStock(anyLong());
        when(prdBookEntityService.save(any())).thenReturn(prdBook);

        prdBookService.addStock(1L, 10L);
    }

    @Test
    public void test_removeStock_successful(){

        PrdBook prdBook = Mockito.mock(PrdBook.class);
        PrdBookStockMovement prdBookStockMovement = Mockito.mock(PrdBookStockMovement.class);

        PrdBookStockMovement prdBookStockMovement1 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement1.getMovementType()).thenReturn(EnumStockMovementType.ADD);
        when(prdBookStockMovement1.getAmount()).thenReturn(100L);

        PrdBookStockMovement prdBookStockMovement2 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement2.getMovementType()).thenReturn(EnumStockMovementType.ADD);
        when(prdBookStockMovement2.getAmount()).thenReturn(10L);

        PrdBookStockMovement prdBookStockMovement3 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement3.getMovementType()).thenReturn(EnumStockMovementType.REMOVE);
        when(prdBookStockMovement3.getAmount()).thenReturn(1L);

        List<PrdBookStockMovement> prdBookStockMovementList = new ArrayList<>();
        prdBookStockMovementList.add(prdBookStockMovement1);
        prdBookStockMovementList.add(prdBookStockMovement2);
        prdBookStockMovementList.add(prdBookStockMovement3);

        doNothing().when(prdBookValidator).validateAmount(anyLong());
        when(prdBookEntityService.getById(anyLong())).thenReturn(prdBook);
        when(prdBookStockMovementEntityService.save(any())).thenReturn(prdBookStockMovement);
        when(prdBookStockMovementEntityService.findAllByPrdBookId(anyLong())).thenReturn(prdBookStockMovementList);
        doNothing().when(prdBookValidator).validateNewStock(anyLong());
        when(prdBookEntityService.save(any())).thenReturn(prdBook);

        prdBookService.removeStock(1L, 10L);
    }

    @Test
    public void test_addStockAndGetNewStock_successful(){

        PrdBook prdBook = Mockito.mock(PrdBook.class);
        PrdBookStockMovement prdBookStockMovement = Mockito.mock(PrdBookStockMovement.class);

        PrdBookStockMovement prdBookStockMovement1 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement1.getMovementType()).thenReturn(EnumStockMovementType.ADD);
        when(prdBookStockMovement1.getAmount()).thenReturn(100L);

        PrdBookStockMovement prdBookStockMovement2 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement2.getMovementType()).thenReturn(EnumStockMovementType.ADD);
        when(prdBookStockMovement2.getAmount()).thenReturn(10L);

        PrdBookStockMovement prdBookStockMovement3 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement3.getMovementType()).thenReturn(EnumStockMovementType.REMOVE);
        when(prdBookStockMovement3.getAmount()).thenReturn(1L);

        List<PrdBookStockMovement> prdBookStockMovementList = new ArrayList<>();
        prdBookStockMovementList.add(prdBookStockMovement1);
        prdBookStockMovementList.add(prdBookStockMovement2);
        prdBookStockMovementList.add(prdBookStockMovement3);

        doNothing().when(prdBookValidator).validateAmount(anyLong());
        when(prdBookEntityService.getById(anyLong())).thenReturn(prdBook);
        when(prdBookStockMovementEntityService.save(any())).thenReturn(prdBookStockMovement);
        when(prdBookStockMovementEntityService.findAllByPrdBookId(anyLong())).thenReturn(prdBookStockMovementList);
        doNothing().when(prdBookValidator).validateNewStock(anyLong());
        when(prdBookEntityService.save(any())).thenReturn(prdBook);

        prdBookService.addStockAndGetNewStock(1L, 10L);
    }

    @Test
    public void test_removeStockAndGetNewStock_successful(){

        PrdBook prdBook = Mockito.mock(PrdBook.class);
        PrdBookStockMovement prdBookStockMovement = Mockito.mock(PrdBookStockMovement.class);

        PrdBookStockMovement prdBookStockMovement1 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement1.getMovementType()).thenReturn(EnumStockMovementType.ADD);
        when(prdBookStockMovement1.getAmount()).thenReturn(100L);

        PrdBookStockMovement prdBookStockMovement2 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement2.getMovementType()).thenReturn(EnumStockMovementType.ADD);
        when(prdBookStockMovement2.getAmount()).thenReturn(10L);

        PrdBookStockMovement prdBookStockMovement3 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement3.getMovementType()).thenReturn(EnumStockMovementType.REMOVE);
        when(prdBookStockMovement3.getAmount()).thenReturn(1L);

        List<PrdBookStockMovement> prdBookStockMovementList = new ArrayList<>();
        prdBookStockMovementList.add(prdBookStockMovement1);
        prdBookStockMovementList.add(prdBookStockMovement2);
        prdBookStockMovementList.add(prdBookStockMovement3);

        doNothing().when(prdBookValidator).validateAmount(anyLong());
        when(prdBookEntityService.getById(anyLong())).thenReturn(prdBook);
        when(prdBookStockMovementEntityService.save(any())).thenReturn(prdBookStockMovement);
        when(prdBookStockMovementEntityService.findAllByPrdBookId(anyLong())).thenReturn(prdBookStockMovementList);
        doNothing().when(prdBookValidator).validateNewStock(anyLong());
        when(prdBookEntityService.save(any())).thenReturn(prdBook);

        prdBookService.removeStockAndGetNewStock(1L, 10L);
    }

    @Test
    public void test_calculateStock_successful(){

        PrdBookStockMovement prdBookStockMovement1 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement1.getMovementType()).thenReturn(EnumStockMovementType.ADD);
        when(prdBookStockMovement1.getAmount()).thenReturn(100L);

        PrdBookStockMovement prdBookStockMovement2 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement2.getMovementType()).thenReturn(EnumStockMovementType.ADD);
        when(prdBookStockMovement2.getAmount()).thenReturn(10L);

        PrdBookStockMovement prdBookStockMovement3 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement3.getMovementType()).thenReturn(EnumStockMovementType.REMOVE);
        when(prdBookStockMovement3.getAmount()).thenReturn(1L);

        List<PrdBookStockMovement> prdBookStockMovementList = new ArrayList<>();
        prdBookStockMovementList.add(prdBookStockMovement1);
        prdBookStockMovementList.add(prdBookStockMovement2);
        prdBookStockMovementList.add(prdBookStockMovement3);

        when(prdBookStockMovementEntityService.findAllByPrdBookId(Mockito.anyLong())).thenReturn(prdBookStockMovementList);

        Long stock = prdBookService.calculateStock(anyLong());

        assertEquals(stock, 109L);
    }

    @Test
    public void test_calculateStock_failForWhenMovementTypeIsNull(){

        PrdBookStockMovement prdBookStockMovement3 = Mockito.mock(PrdBookStockMovement.class);
        when(prdBookStockMovement3.getMovementType()).thenReturn(null);
        when(prdBookStockMovement3.getAmount()).thenReturn(1L);

        List<PrdBookStockMovement> prdBookStockMovementList = new ArrayList<>();
        prdBookStockMovementList.add(prdBookStockMovement3);

        when(prdBookStockMovementEntityService.findAllByPrdBookId(Mockito.anyLong())).thenReturn(prdBookStockMovementList);

        assertThrows(GenBusinessException.class, () -> prdBookService.calculateStock(anyLong()));

    }
}