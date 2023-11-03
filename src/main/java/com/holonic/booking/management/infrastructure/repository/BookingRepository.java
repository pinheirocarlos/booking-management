package com.holonic.booking.management.infrastructure.repository;

import com.holonic.booking.management.application.exception.BusinessException;
import com.holonic.booking.management.application.mapper.ApplicationMapper;
import com.holonic.booking.management.domain.Booking;
import com.holonic.booking.management.infrastructure.entity.BookingEntity;
import com.holonic.booking.management.infrastructure.repository.jpa.BookingJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingRepository {

    private final BookingJpaRepository bookingJpaRepository;
    private final ApplicationMapper applicationMapper;

    public BookingRepository(BookingJpaRepository bookingJpaRepository, ApplicationMapper applicationMapper) {
        this.bookingJpaRepository = bookingJpaRepository;
        this.applicationMapper = applicationMapper;
    }

    public List<Booking> findAll() {
        List<BookingEntity> all = this.bookingJpaRepository.findAll();
        return applicationMapper.fromEntityListToBookingModelList(all);
    }

    public void deleteById(Booking booking) {
        BookingEntity bookingEntity = applicationMapper.fromModelToBookingEntity(booking);
        this.bookingJpaRepository.save(bookingEntity);
    }

    public Booking findByIdOrThrowError(int id) {
        BookingEntity booking = this.bookingJpaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Invalid booking id: " + id));
        return applicationMapper.fromEntityToBookingModel(booking);
    }

    public List<Booking> findByPropertyIdAndStatus(int propertyId, Booking.BookingStatus status){
        List<BookingEntity> byPropertyIdAndStatus = this.bookingJpaRepository.findByIdPropertyAndStatus(propertyId, status);
        return applicationMapper.fromEntityListToBookingModelList(byPropertyIdAndStatus);
    }

    public Booking save(Booking booking) {
        BookingEntity bookingEntity = applicationMapper.fromModelToBookingEntity(booking);
        BookingEntity bookingEntitySaved = bookingJpaRepository
                .save(bookingEntity);
        return applicationMapper.fromEntityToBookingModel(bookingEntitySaved);
    }

}
