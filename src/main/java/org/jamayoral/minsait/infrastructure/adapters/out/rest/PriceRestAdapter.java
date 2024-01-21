package org.jamayoral.minsait.infrastructure.adapters.out.rest;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jamayoral.minsait.application.ports.in.GetPriceUseCase;
import org.jamayoral.minsait.infrastructure.adapters.out.rest.dto.PriceResponseDto;
import org.jamayoral.minsait.infrastructure.adapters.out.rest.mapper.PriceDtoMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/prices")
@AllArgsConstructor
@Tag(name = "Prices", description = "Prices API")
@Slf4j
public class PriceRestAdapter {

    private final GetPriceUseCase getPriceUseCase;

    private final PriceDtoMapper priceDtoMapper;

    @GetMapping("/")
    @ApiResponse(responseCode = "200",
                 description = "Price determined for the given input parameters.",
                 content = @Content(mediaType = "application/json",
                 schema = @Schema(type = "object", implementation = PriceResponseDto.class)))
    @ApiResponse(responseCode = "204",
                 description = "No price found for the provided input parameters.",
                 content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400",
                 description = "Bad request",
                 content = @Content(mediaType = "application/json"))
    public ResponseEntity<PriceResponseDto> getPrice(
            @RequestParam @Parameter(description = "Date with format yyyy-MM-ddTHH:mm:ss",
                                     example = "2020-06-14T16:00:00")
            @DateTimeFormat(iso = ISO.DATE_TIME) final LocalDateTime date,
            @RequestParam @Parameter(description = "Product Id", example = "35455") final Long productId,
            @RequestParam @Parameter(description = "Brand Id", example = "1") final Long brandId) {

        log.debug("getPrice(date={},productId={},brandId={})", date, productId, brandId);

        return getPriceUseCase.getPrice(date, productId, brandId)
                .map(priceDtoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}