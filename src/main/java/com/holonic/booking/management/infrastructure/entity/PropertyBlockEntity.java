package com.holonic.booking.management.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "property_blocks")
@Data
public class PropertyBlockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "id_property")
    Integer idProperty;
    @Column(name = "start_date")
    LocalDate startDate;
    @Column(name = "end_date")
    LocalDate endDate;

    @Column(name = "notes")
    String notes;

}
