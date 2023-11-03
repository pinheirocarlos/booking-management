package com.holonic.booking.management.infrastructure.repository.jpa;

import com.holonic.booking.management.infrastructure.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyJpaRepository extends JpaRepository<PropertyEntity, Integer> {
    Optional<PropertyEntity> findById(int id);

    List<PropertyEntity> findByIdOwner(int id);
}
