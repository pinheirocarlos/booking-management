package com.holonic.booking.management.infrastructure.repository.jpa;

import com.holonic.booking.management.infrastructure.entity.PropertyBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyBlockJpaRepository extends JpaRepository<PropertyBlockEntity, Integer> {
    List<PropertyBlockEntity> findByIdProperty(Integer propertyId);

}
