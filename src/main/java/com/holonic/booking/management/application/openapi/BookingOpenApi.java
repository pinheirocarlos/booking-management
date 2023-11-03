package com.holonic.booking.management.application.openapi;

import com.holonic.booking.management.application.dto.request.PostBookingRequest;
import com.holonic.booking.management.application.dto.request.PutBookingRequest;
import com.holonic.booking.management.domain.Booking;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Booking")
public interface BookingOpenApi {
    @Operation(summary = "List all bookings")
    public List<Booking> findAll();

    @Operation(summary = "Create a new booking")
    public Booking createBooking(PostBookingRequest request);

    @Operation(summary = "Deletes a booking by ID")
    public void deleteBooking(int id);

    @Operation(summary = "Updates a booking by ID")
    public Booking updateBooking(int id,
                                 PutBookingRequest request);
}
