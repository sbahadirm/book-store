package com.bahadirmemis.bookstrore.bookstore.ord.controller;

import com.bahadirmemis.bookstrore.bookstore.gen.dto.RestResponse;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderDto;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.ord.service.OrdOrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdOrderController {

    private final OrdOrderService ordOrderService;

    @GetMapping
    @Operation(tags = "Order Controller")
    public ResponseEntity getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){

        List<OrdOrderDto> ordOrderDtoList = ordOrderService.findAll(page, size);

        return ResponseEntity.ok(RestResponse.of(ordOrderDtoList));
    }

    @GetMapping("/{id}")
    @Operation(tags = "Order Controller")
    public ResponseEntity findById(@PathVariable Long id){

        OrdOrderDto ordOrderDto = ordOrderService.findById(id);

        return ResponseEntity.ok(RestResponse.of(ordOrderDto));
    }

    @PostMapping
    @Operation(tags = "Order Controller")
    public ResponseEntity create(@Valid @RequestBody OrdOrderSaveRequestDto ordOrderSaveRequestDto){

        OrdOrderDto ordOrderDto = ordOrderService.save(ordOrderSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(ordOrderDto));
    }

    @DeleteMapping("/{id}")
    @Operation(tags = "Order Controller")
    public void delete(@PathVariable Long id){
        ordOrderService.delete(id);
    }

    @GetMapping("/given-range")
    @Operation(tags = "Order Controller", description = "Gets all orders of the current customers with in a given range")
    public ResponseEntity getAllOrdersByOrdOrderDateRangeDto(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate){

        List<OrdOrderDto> ordOrderDtoList = ordOrderService.findAllOrdersByGivenRange(startDate, endDate);

        return ResponseEntity.ok(RestResponse.of(ordOrderDtoList));
    }

    @GetMapping("/customers/{customerId}")
    @Operation(tags = "Order Controller", description = "Gets all orders of the current customers")
    public ResponseEntity getAllOrdersByCustomerId(@PathVariable Long customerId, Optional<Integer> pageOptional, Optional<Integer> sizeOptional){

        List<OrdOrderDto> ordOrderDtoList = ordOrderService.getAllOrdersByCustomerId(customerId, pageOptional, sizeOptional);

        return ResponseEntity.ok(RestResponse.of(ordOrderDtoList));
    }
}
