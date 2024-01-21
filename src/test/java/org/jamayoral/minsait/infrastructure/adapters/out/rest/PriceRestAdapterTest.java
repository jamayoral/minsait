package org.jamayoral.minsait.infrastructure.adapters.out.rest;

import org.jamayoral.minsait.application.ports.in.GetPriceUseCase;
import org.jamayoral.minsait.domain.model.*;
import org.jamayoral.minsait.infrastructure.adapters.out.rest.dto.PriceResponseDto;
import org.jamayoral.minsait.infrastructure.adapters.out.rest.mapper.PriceDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PriceRestAdapter.class)
@DisplayName("Test Unitario sobre la API REST")
public class PriceRestAdapterTest {

    @MockBean
    private GetPriceUseCase getPriceUseCase;

    @MockBean
    private PriceDtoMapper priceDtoMapper;

    @Autowired
    private MockMvc mockMvc;

    private final LocalDateTime now = LocalDateTime.now();
    private final Long existingProductId = 1L;
    private final Long existingBrandId = 1L;

    @Test
    @DisplayName("Test: Obtener precio existente")
    public void testGetPriceFound() throws Exception {
        // Mock del comportamiento del caso de uso
        when(getPriceUseCase.getPrice(now, existingProductId, existingBrandId))
                .thenReturn(Optional.of(createSamplePrice()));

        // Mock del comportamiento del mapper
        when(priceDtoMapper.toDto(createSamplePrice())).thenReturn(createSamplePriceDto());

        // Test de la API
        mockMvc.perform(get("/v1/prices/")
                        .param("date", now.toString())
                        .param("productId", existingProductId.toString())
                        .param("brandId", existingBrandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @DisplayName("Test: No hay precio para los parámetros dados")
    public void testGetPriceNotFound() throws Exception {
        final Long nonExistingProductId = 2L;
        final Long nonExistingBrandId = 2L;

        when(getPriceUseCase.getPrice(now, nonExistingProductId, nonExistingBrandId))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/v1/prices/")
                        .param("date", now.toString())
                        .param("productId", nonExistingProductId.toString())
                        .param("brandId", nonExistingBrandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private Price createSamplePrice() {
        return new Price(
                new BrandId(existingBrandId),
                now,
                now.plusHours(1),
                new PriceList(1L),
                new ProductId(existingProductId),
                new Priority(1),
                BigDecimal.valueOf(50.0),
                new Currency("USD")
        );
    }

    private PriceResponseDto createSamplePriceDto() {
        return new PriceResponseDto(
                existingProductId,
                existingBrandId,
                1L,
                now,
                now.plusHours(1),
                BigDecimal.valueOf(50.0)
        );
    }
}
