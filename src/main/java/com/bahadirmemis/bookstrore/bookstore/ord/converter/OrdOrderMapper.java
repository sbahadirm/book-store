package com.bahadirmemis.bookstrore.bookstore.ord.converter;

import com.bahadirmemis.bookstrore.bookstore.gen.enums.GenEnumMonth;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdMonthlyStatisticsDto;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdMonthlyStatisticsResponseDto;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderDto;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.ord.entity.OrdOrder;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrdOrderMapper {

    OrdOrderMapper INSTANCE = Mappers.getMapper(OrdOrderMapper.class);

    OrdOrderDto convertToOrdOrderDto(OrdOrder ordOrder);

    List<OrdOrderDto> convertToOrdOrderDtoList(List<OrdOrder> ordOrderList);

    OrdOrder convertToOrdOrder(OrdOrderSaveRequestDto ordOrderSaveRequestDto);

    @Mapping(target = "year", source = "orderYear")
    OrdMonthlyStatisticsResponseDto convertToOrdMonthlyStatisticsResponseDto(OrdMonthlyStatisticsDto ordMonthlyStatisticsDto);

    List<OrdMonthlyStatisticsResponseDto> convertToOrdMonthlyStatisticsResponseDtoList(List<OrdMonthlyStatisticsDto> ordMonthlyStatisticsDtoList);

    @AfterMapping
    default void setGenEnumMonth(@MappingTarget OrdMonthlyStatisticsResponseDto monthlyStatisticsResponseDto,
                                 OrdMonthlyStatisticsDto ordMonthlyStatisticsDto){

        Integer month = ordMonthlyStatisticsDto.getOrderMonth();
        if (month != null){
            GenEnumMonth genEnumMonth = GenEnumMonth.valueOf(month);
            monthlyStatisticsResponseDto.setMonth(genEnumMonth);
        }

    }
}
