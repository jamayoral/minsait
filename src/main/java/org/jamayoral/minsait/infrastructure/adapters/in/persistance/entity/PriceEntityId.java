package org.jamayoral.minsait.infrastructure.adapters.in.persistance.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PriceEntityId implements Serializable{

    @Column(name = "product_id")
    private Long productId;
    @Column(name = "brand_id")
    private Long brandId;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "priority")
    private Integer priority;

}
