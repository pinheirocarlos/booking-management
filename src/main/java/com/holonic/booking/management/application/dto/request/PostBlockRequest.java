package com.holonic.booking.management.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Representation of a new block", name = "PostBlockRequest")
public record PostBlockRequest(
        @Schema(description = "ID property", example = "1")
        int idProperty,
        @Schema(description = "Block start date in YYYY-MM-DD", example = "2023-11-01")
        LocalDate startDate,
        @Schema(description = "Block end date in YYYY-MM-DD", example = "2023-10-15")
        LocalDate endDate,
        @Schema(description = "Owner ID", example = "1")
        int idOwner,
        @Schema(description = "Notes from idOwner", example = "Repainting rooms.")
        String notes
) {
}
