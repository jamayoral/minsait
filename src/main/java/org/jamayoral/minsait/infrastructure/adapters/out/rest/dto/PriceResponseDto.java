package org.jamayoral.minsait.infrastructure.adapters.out.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "DTO que representa la respuesta del precio")
public record PriceResponseDto(
        @Schema(description = "Id del producto") Long productId,
        @Schema(description = "Id de la marca") Long brandId,
        @Schema(description = "Id de la lista de precios") Long priceList,
        @Schema(description = "Fecha de inicio del precio") LocalDateTime startDate,
        @Schema(description = "Fecha de fin del precio") LocalDateTime endDate,
        @Schema(description = "Precio")
        BigDecimal price
) {
}
