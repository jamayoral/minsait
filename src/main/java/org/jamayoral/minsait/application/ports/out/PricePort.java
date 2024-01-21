package org.jamayoral.minsait.application.ports.out;

import org.jamayoral.minsait.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PricePort {

    Optional<Price> findPrice(LocalDateTime date, Long productId, Long brandId);

}
