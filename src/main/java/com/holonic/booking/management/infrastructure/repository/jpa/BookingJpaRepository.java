package com.holonic.booking.management.infrastructure.repository.jpa;

import com.holonic.booking.management.domain.Booking;
import com.holonic.booking.management.infrastructure.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingJpaRepository extends JpaRepository<BookingEntity, Integer> {

    List<BookingEntity> findAll();
    List<BookingEntity> findByIdPropertyAndStatus(int idProperty, Booking.BookingStatus status);
}
