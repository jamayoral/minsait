package org.jamayoral.minsait.infrastructure.adapters.in.persistance;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jamayoral.minsait.application.ports.out.PricePort;
import org.jamayoral.minsait.domain.model.Price;
import org.jamayoral.minsait.infrastructure.adapters.in.persistance.repository.PriceRepository;
import org.jamayoral.minsait.infrastructure.adapters.in.persistance.mapper.PriceEntityMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class PricePortAdapter implements PricePort {

    private final PriceRepository priceRepository;
    private final PriceEntityMapper priceEntityMapper;

    @Override
    public Optional<Price> findPrice(final LocalDateTime date, final Long productId, final Long brandId) {
        log.debug("findPrice(appDate={},productId={},brandId={})", date, productId, brandId);

        return priceRepository.findPrice(date, productId, brandId)
                .map(priceEntityMapper::toDomain);
    }
}
