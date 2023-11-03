package com.holonic.booking.management.application.service;

import com.holonic.booking.management.application.dto.request.PostBookingRequest;
import com.holonic.booking.management.application.dto.request.PutBookingRequest;
import com.holonic.booking.management.application.exception.BusinessException;
import com.holonic.booking.management.domain.Booking;
import com.holonic.booking.management.domain.Guest;
import com.holonic.booking.management.domain.Property;
import com.holonic.booking.management.domain.PropertyBlock;
import com.holonic.booking.management.infrastructure.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PropertyBlockRepository propertyBlockRepository;
    private final GuestRepository guestRepository;
    private final OwnerRepository ownerRepository;
    private final PropertyRepository propertyRepository;

    private static Logger log = LoggerFactory.getLogger(Booking.class);

    public BookingService(BookingRepository bookingRepository, PropertyBlockRepository propertyBlockRepository, GuestRepository guestRepository, OwnerRepository ownerRepository, PropertyRepository propertyRepository) {
        this.bookingRepository = bookingRepository;
        this.propertyBlockRepository = propertyBlockRepository;
        this.guestRepository = guestRepository;
        this.ownerRepository = ownerRepository;
        this.propertyRepository = propertyRepository;
    }

    public List<Booking> findAll() {
        return this.bookingRepository.findAll();
    }

    public void delete(int id) {
        Booking booking = bookingRepository.findByIdOrThrowError(id);
        Booking deletedBooking = new Booking(booking.id(),
                booking.idProperty(),
                booking.guest(),
                booking.startDate(),
                booking.endDate(),
                Booking.BookingStatus.CANCELLED,
                booking.notes());
        this.bookingRepository.deleteById(deletedBooking);
    }

    public Booking create(PostBookingRequest request) {
        this.validateDates(request.startDate(), request.endDate());
        Property property = propertyRepository.findByIdPropertyOrThrowError(request.idProperty());
        Guest guest = guestRepository.findByUserIdOrThrowError(request.idGuest());
        checkPropertyBlock(request.idProperty(), request.startDate(), request.endDate());
        checkBookingCreate(request.idProperty(), request.startDate(), request.endDate());

        Booking newBooking = new Booking(null,
                property.id(),
                guest,
                request.startDate(),
                request.endDate(),
                Booking.BookingStatus.ACTIVE,
                request.notes());

        Booking bookingSaved = bookingRepository.save(newBooking);

        return bookingSaved;
    }

    public Booking update(int id, PutBookingRequest request) {
        this.validateDates(request.startDate(), request.endDate());
        Booking booking = bookingRepository.findByIdOrThrowError(id);
        checkPropertyBlock(booking.idProperty(), request.startDate(), request.endDate());
        checkBookingUpdate(booking.idProperty(), request.startDate(), request.endDate(), request.idGuest());
        Guest guest = guestRepository.findByUserIdOrThrowError(request.idGuest());
        Guest guestSaving = new Guest(guest.id(), request.name());

        Booking newBooking = new Booking(id,
                booking.idProperty(),
                guestSaving,
                request.startDate(),
                request.endDate(),
                request.status(),
                request.notes());

        Booking bookingSaved = bookingRepository.save(newBooking);
        return bookingSaved;
    }

    private void checkPropertyBlock(int propertyId, LocalDate startDate, LocalDate endDate) {
        List<PropertyBlock> propertyBlocksList = propertyBlockRepository.findByIdProperty(propertyId);

        boolean isPropertyBlockAvailable = propertyBlocksList.stream()
                .noneMatch((it) -> startDate.isBefore(it.endDate()) && it.startDate().isBefore(endDate));

        if (!isPropertyBlockAvailable) {
            throw new BusinessException("The property " + propertyId + " is blocked for this period. Please try another date.");
        }
    }

    public void checkBookingCreate(int propertyId, LocalDate startDate, LocalDate endDate) {
        List<Booking> byPropertyIdAndStatus = bookingRepository.findByPropertyIdAndStatus(propertyId, Booking.BookingStatus.ACTIVE);

        boolean isBookingAvailable = byPropertyIdAndStatus.stream()
                .noneMatch((it) -> startDate.isBefore(it.endDate())
                        && it.startDate().isBefore(endDate));
        if (!isBookingAvailable) {
            throw new BusinessException("The property " + propertyId + " is already booked for this period. Please try another date.");
        }
    }

    public void checkBookingUpdate(int propertyId, LocalDate startDate, LocalDate endDate, int idGuest) {
        List<Booking> byPropertyIdAndStatus = bookingRepository.findByPropertyIdAndStatus(propertyId, Booking.BookingStatus.ACTIVE);

        boolean isBookingAvailable = byPropertyIdAndStatus.stream()
                .noneMatch((it) -> startDate.isBefore(it.endDate())
                        && it.startDate().isBefore(endDate)
                        && it.guest().id() != idGuest);
        if (!isBookingAvailable) {
            throw new BusinessException("The property " + propertyId + " is already booked for this period. Please try another date.");
        }
    }

    private void validateDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new BusinessException("Null booking dates passed" +
                    ". startDate: " + startDate +
                    ". endDate: " + endDate);
        }

        if (startDate.isBefore(LocalDate.now())
                || endDate.isBefore(LocalDate.now())
                || endDate.isBefore(startDate)
        ) {
            throw new BusinessException("Error validating booking dates" +
                    ". startDate: " + startDate +
                    ". endDate: " + endDate);
        }

    }
}
