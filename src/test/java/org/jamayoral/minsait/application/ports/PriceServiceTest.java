package org.jamayoral.minsait.application.ports;

import org.jamayoral.minsait.application.ports.out.PricePort;
import org.jamayoral.minsait.domain.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Unitario del servicio Price")
class PriceServiceTest {

    @Mock
    private PricePort pricePort;

    @InjectMocks
    private PriceService priceService;

    @Test
    @DisplayName("Test: Obtener precio existente")
    void testGetExistingPrice() {
        final LocalDateTime appDate = LocalDateTime.now();
        final Long productId = 1L;
        final Long brandId = 1L;

        final Price expectedPrice = createSamplePrice(brandId, productId);
        when(pricePort.findPrice(appDate, productId, brandId)).thenReturn(Optional.of(expectedPrice));

        final Optional<Price> result = priceService.getPrice(appDate, productId, brandId);

        assertEquals(Optional.of(expectedPrice), result);
    }

    @Test
    @DisplayName("Test: Obtener precio inexistente")
    void testGetNonExistentPrice() {
        final LocalDateTime appDate = LocalDateTime.now();
        final Long productId = 2L;
        final Long brandId = 2L;

        when(pricePort.findPrice(appDate, productId, brandId)).thenReturn(Optional.empty());

        final Optional<Price> result = priceService.getPrice(appDate, productId, brandId);

        assertEquals(Optional.empty(), result);
    }

    // Método para crear una instancia de Price para pruebas
    private Price createSamplePrice(Long brandId, Long productId) {
        return new Price(
                new BrandId(brandId),
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                new PriceList(1L),
                new ProductId(productId),
                new Priority(1),
                new BigDecimal("50.0"),
                new Currency("USD")
        );
    }
}
