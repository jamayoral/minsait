package org.jamayoral.minsait.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(BrandId brandId, LocalDateTime startDate, LocalDateTime endDate,
                    PriceList priceList, ProductId productId, Priority priority, BigDecimal price, Currency currency) {
}
