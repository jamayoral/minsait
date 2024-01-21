package org.jamayoral.minsait.application.ports;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jamayoral.minsait.application.ports.in.GetPriceUseCase;
import org.jamayoral.minsait.application.ports.out.PricePort;
import org.jamayoral.minsait.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@Slf4j
public class PriceService implements GetPriceUseCase {

    private final PricePort pricePort;

    @Override
    public Optional<Price> getPrice(final LocalDateTime date, final Long productId, final Long brandId) {
        log.debug("getPrice(appDate={},productId={},brandId={})", date, productId, brandId);

        return pricePort.findPrice(date, productId, brandId);
    }
}
