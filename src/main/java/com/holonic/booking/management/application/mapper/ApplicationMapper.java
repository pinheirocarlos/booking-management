package com.holonic.booking.management.application.mapper;

import com.holonic.booking.management.domain.*;
import com.holonic.booking.management.infrastructure.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicationMapper {
    List<Booking> fromEntityListToBookingModelList(List<BookingEntity> bookingList);

    List<PropertyBlock> fromEntityListToPropertyBlockModelList(List<PropertyBlockEntity> propertyBlockEntities);
    Booking fromEntityToBookingModel(BookingEntity booking);

    BookingEntity fromModelToBookingEntity(Booking booking);

    PropertyEntity fromModelToPropertyEntity(Property property);

    PropertyBlock fromEntityToPropertyBlockModel(PropertyBlockEntity propertyBlockEntity);
    PropertyBlockEntity fromModelToPropertyBlockEntity(PropertyBlock propertyBlock);

    Property fromEntityToPropertyModel(PropertyEntity propertyEntity);
    List<Property> fromEntityListToPropertyListModel(List<PropertyEntity> propertyEntity);

    Guest fromEntityToPropertyModel(GuestEntity guestEntity);

    Owner fromEntityToOwnerModel(OwnerEntity ownerEntity);

}
