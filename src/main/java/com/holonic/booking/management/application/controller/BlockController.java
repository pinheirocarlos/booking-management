package com.holonic.booking.management.application.controller;

import com.holonic.booking.management.application.dto.request.PostBlockRequest;
import com.holonic.booking.management.application.dto.request.PutBlockRequest;
import com.holonic.booking.management.application.openapi.BlockOpenApi;
import com.holonic.booking.management.application.service.PropertyBlockService;
import com.holonic.booking.management.domain.PropertyBlock;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/block")
public class BlockController implements BlockOpenApi {

    private final PropertyBlockService propertyBlockService;

    public BlockController(PropertyBlockService propertyBlockService) {
        this.propertyBlockService = propertyBlockService;
    }

    @Override
    @GetMapping
    public List<PropertyBlock> findAll() {
        return propertyBlockService.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PropertyBlock createBlock(@RequestBody PostBlockRequest request) {
        return propertyBlockService.save(request);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBlock(@PathVariable int id) {
        propertyBlockService.deleteById(id);
    }

    @Override
    @PutMapping("/{id}")
    public PropertyBlock updateBlock(@PathVariable int id, @RequestBody PutBlockRequest request) {
        return propertyBlockService.updateById(id, request);
    }
}
