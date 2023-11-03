package com.holonic.booking.management.infrastructure.entity;

import com.holonic.booking.management.domain.Booking;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "booking")
@Data
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = GuestEntity.class)
    @JoinColumn(name = "id_guest")
    GuestEntity guest;
    @Column(name = "id_property")
    Integer idProperty;
    @Column(name = "start_date")
    LocalDate startDate;
    @Column(name = "end_date")
    LocalDate endDate;
    @Enumerated(EnumType.STRING)
    Booking.BookingStatus status;
    String notes;
}
