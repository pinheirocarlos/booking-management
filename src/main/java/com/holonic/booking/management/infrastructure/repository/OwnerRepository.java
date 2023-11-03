package com.holonic.booking.management.infrastructure.repository;

import com.holonic.booking.management.application.exception.BusinessException;
import com.holonic.booking.management.application.mapper.ApplicationMapper;
import com.holonic.booking.management.domain.Owner;
import com.holonic.booking.management.infrastructure.entity.OwnerEntity;
import com.holonic.booking.management.infrastructure.repository.jpa.OwnerJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerRepository {
    private final OwnerJpaRepository ownerJpaRepository;
    private final ApplicationMapper applicationMapper;

    public OwnerRepository(OwnerJpaRepository ownerJpaRepository, ApplicationMapper applicationMapper) {
        this.ownerJpaRepository = ownerJpaRepository;
        this.applicationMapper = applicationMapper;
    }

    public Owner findByOwnerIdOrThrowError(int id) {
        OwnerEntity ownerEntity = ownerJpaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Invalid idOwner id: " + id));
        return applicationMapper.fromEntityToOwnerModel(ownerEntity);
    }
}
