package com.bahadirmemis.bookstrore.bookstore.prd.controller;

import com.bahadirmemis.bookstrore.bookstore.gen.dto.RestResponse;
import com.bahadirmemis.bookstrore.bookstore.prd.dto.PrdBookDto;
import com.bahadirmemis.bookstrore.bookstore.prd.dto.PrdBookSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.prd.service.PrdBookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class PrdBookController {

    private final PrdBookService prdBookService;

    @GetMapping
    @Operation(tags = "Book Controller")
    public ResponseEntity getAll(){

        List<PrdBookDto> prdBookDtoList = prdBookService.findAll();

        return ResponseEntity.ok(RestResponse.of(prdBookDtoList));
    }

    @PostMapping
    @Operation(tags = "Book Controller")
    public ResponseEntity create(@Valid @RequestBody PrdBookSaveRequestDto prdBookSaveRequestDto){

        PrdBookDto prdBookDto = prdBookService.save(prdBookSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(prdBookDto));
    }

    @DeleteMapping("/{id}")
    @Operation(tags = "Book Controller")
    public ResponseEntity delete(@PathVariable Long id){

        prdBookService.delete(id);

        return ResponseEntity.ok(RestResponse.empty());
    }

    @PostMapping("/{id}/stocks/add")
    @Operation(tags = "Book Controller")
    public ResponseEntity addStock(@PathVariable Long id, Long amount){

        Long newStock = prdBookService.addStockAndGetNewStock(id, amount);

        return ResponseEntity.ok(RestResponse.of(newStock));
    }

    @PostMapping("/{id}/stocks/remove")
    @Operation(tags = "Book Controller")
    public ResponseEntity removeStock(@PathVariable Long id, Long amount){

        Long newStock = prdBookService.removeStockAndGetNewStock(id, amount);

        return ResponseEntity.ok(RestResponse.of(newStock));
    }

    @GetMapping("/{id}/stocks")
    @Operation(tags = "Book Controller")
    public ResponseEntity getStock(@PathVariable Long id){

        Long stock = prdBookService.calculateStock(id);

        return ResponseEntity.ok(RestResponse.of(stock));
    }
}
