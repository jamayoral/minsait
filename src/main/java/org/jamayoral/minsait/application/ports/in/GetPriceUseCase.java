package org.jamayoral.minsait.application.ports.in;

import org.jamayoral.minsait.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetPriceUseCase {

    Optional<Price> getPrice(LocalDateTime date, Long productId, Long brandId);
}
