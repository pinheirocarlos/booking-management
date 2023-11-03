package com.holonic.booking.management.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Representation of a block update", name = "PutBlockRequest")
public record PutBlockRequest(
        @Schema(description = "Block start date in YYYY-MM-DD", example = "2023-11-01")
        LocalDate startDate,
        @Schema(description = "Block end date in YYYY-MM-DD", example = "2023-10-15")
        LocalDate endDate,
        @Schema(description = "Notes from idOwner", example = "Repainting rooms.")
        String notes

) {
}
