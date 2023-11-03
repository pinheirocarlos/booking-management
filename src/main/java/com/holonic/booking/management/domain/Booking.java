package com.holonic.booking.management.domain;

import java.time.LocalDate;

public record Booking(

        Integer id,
        Integer idProperty,
        Guest guest,
        LocalDate startDate,
        LocalDate endDate,
        BookingStatus status,
        String notes) {

    public enum BookingStatus {
        ACTIVE,
        CANCELLED
    }
}
