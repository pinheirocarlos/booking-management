package com.holonic.booking.management.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Representation of a new booking", name = "PostBookingRequest")
public record PostBookingRequest(
        @Schema(description = "ID property", example = "1")
        int idProperty,
        @Schema(description = "Booking start date in YYYY-MM-DD", example = "2023-11-01")
        LocalDate startDate,
        @Schema(description = "Booking end date in YYYY-MM-DD", example = "2023-10-15")
        LocalDate endDate,
        @Schema(description = "User ID", example = "1")
        int idGuest,
        @Schema(description = "Notes to idOwner", example = "I'll be arriving at 8")
        String notes
) {
}
