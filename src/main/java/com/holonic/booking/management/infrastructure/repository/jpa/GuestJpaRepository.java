package com.holonic.booking.management.infrastructure.repository.jpa;

import com.holonic.booking.management.infrastructure.entity.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestJpaRepository extends JpaRepository<GuestEntity, Integer> {
    Optional<GuestEntity> findById(int id);
}
