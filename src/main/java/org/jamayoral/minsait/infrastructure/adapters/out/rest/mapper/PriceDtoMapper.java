package org.jamayoral.minsait.infrastructure.adapters.out.rest.mapper;

import org.jamayoral.minsait.domain.model.Price;
import org.jamayoral.minsait.infrastructure.adapters.out.rest.dto.PriceResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") 
public interface PriceDtoMapper {
    
    @Mapping(source = "productId.id", target = "productId")
    @Mapping(source = "brandId.id", target = "brandId")
    @Mapping(source = "priceList.id", target = "priceList")
    PriceResponseDto toDto(Price price);
}
