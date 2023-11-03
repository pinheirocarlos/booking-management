package com.holonic.booking.management.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "property")
@Data
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @Column(name = "id_owner")
    Integer idOwner;
    @ManyToOne
    @JoinColumn(name = "id_owner", insertable = false, updatable = false)
    OwnerEntity owner;
}
