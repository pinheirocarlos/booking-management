package com.holonic.booking.management.domain;

public record Property(
        Integer id,
        String name,
        Integer idOwner
) {
}
