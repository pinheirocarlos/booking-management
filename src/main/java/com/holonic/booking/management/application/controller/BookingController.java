package com.holonic.booking.management.application.controller;

import com.holonic.booking.management.application.dto.request.PostBookingRequest;
import com.holonic.booking.management.application.dto.request.PutBookingRequest;
import com.holonic.booking.management.application.openapi.BookingOpenApi;
import com.holonic.booking.management.application.service.BookingService;
import com.holonic.booking.management.domain.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/booking")
@RestController
public class BookingController implements BookingOpenApi {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    @GetMapping
    public List<Booking> findAll() {
        return this.bookingService.findAll();

    }

    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public Booking createBooking(@RequestBody PostBookingRequest request) {
        return bookingService.create(request);
    }

    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable int id) {
        bookingService.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable int id,
                                 @RequestBody PutBookingRequest request) {
        return bookingService.update(id, request);
    }
}
