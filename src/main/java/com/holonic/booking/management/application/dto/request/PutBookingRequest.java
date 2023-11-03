package com.holonic.booking.management.application.dto.request;

import com.holonic.booking.management.domain.Booking;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Representation of a booking update", name = "PutBookingRequest")
public record PutBookingRequest(
        @Schema(description = "Booking start date in YYYY-MM-DD", example = "2023-11-01")
        LocalDate startDate,
        @Schema(description = "Booking end date in YYYY-MM-DD", example = "2023-10-15")
        LocalDate endDate,
        @Schema(description = "Guest ID", example = "20")
        Integer idGuest,
        @Schema(description = "Guest name", example = "John")
        String name,
        @Schema(description = "Notes to idOwner", example = "I'll be arriving at 8")
        String notes,
        @Schema(description = "Booking status", example = "CANCELLED")
        Booking.BookingStatus status
) {
}
