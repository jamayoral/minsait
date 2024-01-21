package org.jamayoral.minsait.infrastructure.adapters.in.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "prices")
@AllArgsConstructor
@NoArgsConstructor
public class PriceEntity {

    @EmbeddedId
    private PriceEntityId id;

    @Column(name = "price_list")
    private Long priceList;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "curr")
    private String currency;
}