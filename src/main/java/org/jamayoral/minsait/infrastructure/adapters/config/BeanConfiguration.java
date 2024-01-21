package org.jamayoral.minsait.infrastructure.adapters.config;

import org.jamayoral.minsait.application.ports.in.GetPriceUseCase;
import org.jamayoral.minsait.application.ports.out.PricePort;
import org.jamayoral.minsait.application.ports.PriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class BeanConfiguration {

    @Bean
    GetPriceUseCase priceService(final PricePort pricePort) {
        return new PriceService(pricePort);
    }
}
