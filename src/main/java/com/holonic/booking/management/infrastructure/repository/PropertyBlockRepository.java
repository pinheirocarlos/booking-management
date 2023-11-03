package com.holonic.booking.management.infrastructure.repository;

import com.holonic.booking.management.application.exception.BusinessException;
import com.holonic.booking.management.application.mapper.ApplicationMapper;
import com.holonic.booking.management.domain.PropertyBlock;
import com.holonic.booking.management.infrastructure.entity.PropertyBlockEntity;
import com.holonic.booking.management.infrastructure.repository.jpa.PropertyBlockJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropertyBlockRepository {
    private final PropertyBlockJpaRepository propertyBlockJpaRepository;
    private final ApplicationMapper applicationMapper;

    public PropertyBlockRepository(PropertyBlockJpaRepository propertyBlockJpaRepository, ApplicationMapper applicationMapper) {
        this.propertyBlockJpaRepository = propertyBlockJpaRepository;
        this.applicationMapper = applicationMapper;
    }

    public List<PropertyBlock> findByIdProperty(int id) {
        List<PropertyBlockEntity> propertyBlockEntity = propertyBlockJpaRepository.findByIdProperty(id);
        return applicationMapper.fromEntityListToPropertyBlockModelList(propertyBlockEntity);
    }

    public PropertyBlock findByIdOrThrowError(int id) {
        PropertyBlockEntity propertyBlockEntity = propertyBlockJpaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Invalid block id: " + id));
        return applicationMapper.fromEntityToPropertyBlockModel(propertyBlockEntity);
    }

    public List<PropertyBlock> findAll() {
        List<PropertyBlockEntity> propertyBlockEntity = propertyBlockJpaRepository.findAll();
        return applicationMapper.fromEntityListToPropertyBlockModelList(propertyBlockEntity);
    }

    public PropertyBlock save(PropertyBlock propertyBlock) {
        PropertyBlockEntity propertyBlockEntity = applicationMapper.fromModelToPropertyBlockEntity(propertyBlock);
        PropertyBlockEntity propertyBlockSaved = propertyBlockJpaRepository.save(propertyBlockEntity);
        return applicationMapper.fromEntityToPropertyBlockModel(propertyBlockSaved);
    }

    public void deleteById(int id){
        propertyBlockJpaRepository.deleteById(id);
    }
}
