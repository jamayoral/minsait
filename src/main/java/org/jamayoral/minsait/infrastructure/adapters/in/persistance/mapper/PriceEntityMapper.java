package org.jamayoral.minsait.infrastructure.adapters.in.persistance.mapper;

import org.jamayoral.minsait.domain.model.Price;
import org.jamayoral.minsait.infrastructure.adapters.in.persistance.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    @Mapping(target="productId.id", source="id.productId")
    @Mapping(target="brandId.id", source="id.brandId")
    @Mapping(target="startDate", source="id.startDate")
    @Mapping(target="endDate", source="id.endDate")
    @Mapping(target="priceList.id", source="priceList")
    @Mapping(target="currency.value", source="currency")
    @Mapping(target="priority.id", source="id.priority")
    Price toDomain(PriceEntity entity);
}