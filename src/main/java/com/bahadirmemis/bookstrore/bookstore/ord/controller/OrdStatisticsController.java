package com.bahadirmemis.bookstrore.bookstore.ord.controller;

import com.bahadirmemis.bookstrore.bookstore.gen.dto.RestResponse;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdMonthlyStatisticsDto;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdMonthlyStatisticsResponseDto;
import com.bahadirmemis.bookstrore.bookstore.ord.service.OrdOrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class OrdStatisticsController {

    private final OrdOrderService ordOrderService;

    @GetMapping
    @Operation(tags = "Statistics Controller", description = "Provides order statistics for the current user")
    public ResponseEntity getMontlyStatistics(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate
    ){

        List<OrdMonthlyStatisticsResponseDto> ordMonthlyStatisticsDtoList = ordOrderService.getMontlyStatistics(startDate, endDate);

        return ResponseEntity.ok(RestResponse.of(ordMonthlyStatisticsDtoList));
    }

}
