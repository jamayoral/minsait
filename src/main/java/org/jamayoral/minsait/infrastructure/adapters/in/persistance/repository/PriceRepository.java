package org.jamayoral.minsait.infrastructure.adapters.in.persistance.repository;

import org.jamayoral.minsait.infrastructure.adapters.in.persistance.entity.PriceEntity;
import org.jamayoral.minsait.infrastructure.adapters.in.persistance.entity.PriceEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, PriceEntityId> {

    @Query("SELECT p FROM PriceEntity p WHERE p.id.productId = :productId AND p.id.brandId = :brandId AND p.id.startDate <= :date AND p.id.endDate >= :date ORDER BY p.id.priority DESC LIMIT 1")
    Optional<PriceEntity> findPrice(@Param("date") LocalDateTime date, @Param("productId") Long productId, @Param("brandId") Long brandId);
}