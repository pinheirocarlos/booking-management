package com.holonic.booking.management.application.service;

import com.holonic.booking.management.application.dto.request.PostBlockRequest;
import com.holonic.booking.management.application.dto.request.PutBlockRequest;
import com.holonic.booking.management.application.exception.BusinessException;
import com.holonic.booking.management.domain.Property;
import com.holonic.booking.management.domain.PropertyBlock;
import com.holonic.booking.management.infrastructure.repository.OwnerRepository;
import com.holonic.booking.management.infrastructure.repository.PropertyBlockRepository;
import com.holonic.booking.management.infrastructure.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyBlockService {
    private final PropertyBlockRepository propertyBlockRepository;
    private final BookingService bookingService;
    private final OwnerRepository ownerRepository;
    private final PropertyRepository propertyRepository;

    public PropertyBlockService(PropertyBlockRepository propertyBlockRepository, BookingService bookingService, OwnerRepository ownerRepository, PropertyRepository propertyRepository) {
        this.propertyBlockRepository = propertyBlockRepository;
        this.bookingService = bookingService;
        this.ownerRepository = ownerRepository;
        this.propertyRepository = propertyRepository;
    }

    public List<PropertyBlock> findAll() {
        return this.propertyBlockRepository.findAll();
    }

    public void deleteById(int id) {
        PropertyBlock propertyBlock = propertyBlockRepository.findByIdOrThrowError(id);
        this.propertyBlockRepository.deleteById(propertyBlock.id());
    }

    public PropertyBlock updateById(int id, PutBlockRequest request){
        PropertyBlock propertyBlock = propertyBlockRepository.findByIdOrThrowError(id);
        bookingService.checkBookingCreate(propertyBlock.idProperty(), request.startDate(), request.endDate());

        PropertyBlock newPropertyBlock = new PropertyBlock(id,
                propertyBlock.idProperty(),
                request.startDate(),
                request.endDate(),
                request.notes());

        PropertyBlock propertyBlockSaved = propertyBlockRepository.save(newPropertyBlock);
        return propertyBlockSaved;
    }

    public PropertyBlock save(PostBlockRequest request){
        ownerRepository.findByOwnerIdOrThrowError(request.idOwner());
        checkPropertyFromOwner(request.idOwner(), request.idProperty());

        bookingService.checkBookingCreate(request.idProperty(), request.startDate(), request.endDate());

        PropertyBlock newPropertyBlock = new PropertyBlock(null,
                request.idProperty(),
                request.startDate(),
                request.endDate(),
                request.notes());

        PropertyBlock propertyBlockSaved = propertyBlockRepository.save(newPropertyBlock);
        return propertyBlockSaved;
    }

    private void checkPropertyFromOwner(int ownerId, int propertyId){
        List<Property> propertiesFromOwnerId = propertyRepository.findByIdOwner(ownerId);

        propertiesFromOwnerId.stream()
                .filter(property -> property.idOwner() == ownerId && property.id() == propertyId)
                .findAny()
                .orElseThrow(() -> new BusinessException("Property id " + propertyId + " does not belong to owner id " + ownerId));
    }
}
