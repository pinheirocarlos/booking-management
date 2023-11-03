package com.holonic.booking.management.infrastructure.repository.jpa;

import com.holonic.booking.management.infrastructure.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerJpaRepository extends JpaRepository<OwnerEntity, Integer> {
    Optional<OwnerEntity> findById(int id);

}
