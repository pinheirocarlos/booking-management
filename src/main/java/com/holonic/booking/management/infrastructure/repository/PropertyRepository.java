package com.holonic.booking.management.infrastructure.repository;

import com.holonic.booking.management.application.exception.BusinessException;
import com.holonic.booking.management.application.mapper.ApplicationMapper;
import com.holonic.booking.management.domain.Property;
import com.holonic.booking.management.infrastructure.entity.PropertyEntity;
import com.holonic.booking.management.infrastructure.repository.jpa.PropertyJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropertyRepository {
    private final PropertyJpaRepository propertyJpaRepository;
    private final ApplicationMapper applicationMapper;

    public PropertyRepository(PropertyJpaRepository propertyJpaRepository, ApplicationMapper applicationMapper) {
        this.propertyJpaRepository = propertyJpaRepository;
        this.applicationMapper = applicationMapper;
    }

    public Property findByIdPropertyOrThrowError(int id) {
        PropertyEntity propertyEntity = propertyJpaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Invalid property id: " + id));
        return applicationMapper.fromEntityToPropertyModel(propertyEntity);
    }

    public List<Property> findByIdOwner(int id) {
        List<PropertyEntity> propertiesByOwner = propertyJpaRepository.findByIdOwner(id);
        return applicationMapper.fromEntityListToPropertyListModel(propertiesByOwner);
    }
}
