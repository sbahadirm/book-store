package com.bahadirmemis.bookstrore.bookstore.prd.converter;

import com.bahadirmemis.bookstrore.bookstore.prd.dto.PrdBookDto;
import com.bahadirmemis.bookstrore.bookstore.prd.dto.PrdBookSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.prd.entity.PrdBook;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrdBookMapper {

    PrdBookMapper INSTANCE = Mappers.getMapper(PrdBookMapper.class);

    PrdBookDto convertToPrdBookDto(PrdBook prdBook);

    List<PrdBookDto> convertToPrdBookDtoList(List<PrdBook> prdBookList);

    PrdBook convertToPrdBook(PrdBookSaveRequestDto prdBookSaveRequestDto);
}
