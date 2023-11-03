package com.holonic.booking.management.application.openapi;

import com.holonic.booking.management.application.dto.request.PostBlockRequest;
import com.holonic.booking.management.application.dto.request.PutBlockRequest;
import com.holonic.booking.management.domain.PropertyBlock;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Block")
public interface BlockOpenApi {
    @Operation(summary = "List all blocks")
    public List<PropertyBlock> findAll();

    @Operation(summary = "Create a new block")
    public PropertyBlock createBlock(PostBlockRequest request);

    @Operation(summary = "Deletes a block by ID")
    public void deleteBlock(int id);

    @Operation(summary = "Updates a block by ID")
    public PropertyBlock updateBlock(int id,
                                 PutBlockRequest request);
}
