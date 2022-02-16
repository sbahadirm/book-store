package com.bahadirmemis.bookstrore.bookstore.sec.controller;

import com.bahadirmemis.bookstrore.bookstore.cus.dto.CusCustomerDto;
import com.bahadirmemis.bookstrore.bookstore.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.cus.service.CusCustomerService;
import com.bahadirmemis.bookstrore.bookstore.gen.dto.RestResponse;
import com.bahadirmemis.bookstrore.bookstore.sec.dto.SecLoginRequestDto;
import com.bahadirmemis.bookstrore.bookstore.sec.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final CusCustomerService cusCustomerService;

    @PostMapping("/login")
    @Operation(tags = "Authentication Controller")
    public ResponseEntity login(@RequestBody SecLoginRequestDto secLoginRequestDto){

        String token = authenticationService.login(secLoginRequestDto);

        return ResponseEntity.ok(RestResponse.of(token));
    }

    @PostMapping("/register")
    @Operation(tags = "Authentication Controller",
//            summary = "Creates new customer",
            description = "Creates new customer ",
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
    public ResponseEntity register(@Valid @RequestBody CusCustomerSaveRequestDto cusCustomerSaveRequestDto){

        CusCustomerDto cusCustomerDto = cusCustomerService.save(cusCustomerSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }

}
