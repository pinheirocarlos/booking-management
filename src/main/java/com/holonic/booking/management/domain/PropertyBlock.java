package com.holonic.booking.management.domain;

import java.time.LocalDate;

public record PropertyBlock(
        Integer id,
        Integer idProperty,
        LocalDate startDate,
        LocalDate endDate,
        String notes
) {
}
