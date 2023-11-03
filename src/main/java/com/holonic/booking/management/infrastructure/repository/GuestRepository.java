package com.holonic.booking.management.infrastructure.repository;

import com.holonic.booking.management.application.exception.BusinessException;
import com.holonic.booking.management.application.mapper.ApplicationMapper;
import com.holonic.booking.management.domain.Guest;
import com.holonic.booking.management.infrastructure.entity.GuestEntity;
import com.holonic.booking.management.infrastructure.repository.jpa.GuestJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GuestRepository {

    private final GuestJpaRepository guestJpaRepository;
    private final ApplicationMapper applicationMapper;

    public GuestRepository(GuestJpaRepository guestJpaRepository, ApplicationMapper applicationMapper) {
        this.guestJpaRepository = guestJpaRepository;
        this.applicationMapper = applicationMapper;
    }

    public Guest findByUserIdOrThrowError(int id) {
        GuestEntity guestEntity = guestJpaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Invalid property id: " + id));
        return applicationMapper.fromEntityToPropertyModel(guestEntity);
    }
}
