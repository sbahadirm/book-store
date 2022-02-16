package com.bahadirmemis.bookstrore.bookstore.cus.controller;

import com.bahadirmemis.bookstrore.bookstore.cus.dto.CusCustomerDto;
import com.bahadirmemis.bookstrore.bookstore.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.cus.service.CusCustomerService;
import com.bahadirmemis.bookstrore.bookstore.gen.dto.RestResponse;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CusCustomerController {

    private final CusCustomerService cusCustomerService;

    @GetMapping
    @Operation(tags = "Customer Controller")
    public ResponseEntity getAll(){

        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();

        return ResponseEntity.ok(RestResponse.of(cusCustomerDtoList));
    }

    @PostMapping
    @Operation(
            tags = "Customer Controller",
//            summary = "Creates new customer",
            description = "Creates new customer",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Creates new customer",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CusCustomerSaveRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new Customer",
                                                    summary = "New Customer Example",
                                                    description = "Complete request with all available fields",
                                                    value = "{\n" +
                                                            "  \"name\": \"bahadir\",\n" +
                                                            "  \"surname\": \"memis\",\n" +
                                                            "  \"username\": \"sbahadirm\",\n" +
                                                            "  \"password\": \"12345678\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    public ResponseEntity create(@Valid @RequestBody CusCustomerSaveRequestDto cusCustomerSaveRequestDto){

        CusCustomerDto cusCustomerDto = cusCustomerService.save(cusCustomerSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }

    @DeleteMapping("/{id}")
    @Operation(tags = "Customer Controller")
    public ResponseEntity delete(@PathVariable Long id){

        cusCustomerService.delete(id);

        return ResponseEntity.ok(RestResponse.empty());
    }

    @GetMapping("/orders")
    @Operation(tags = "Customer Controller",
//            summary = "Gets all orders",
            description = "Retrieves all orders of the current customer with pagination")
    public ResponseEntity getAllOrdersByCurrentCustomer(Optional<Integer> pageOptional, Optional<Integer> sizeOptional){

        List<OrdOrderDto> ordOrderDtoList = cusCustomerService.getAllOrdersByCurrentCustomer(pageOptional, sizeOptional);

        return ResponseEntity.ok(RestResponse.of(ordOrderDtoList));
    }
}
