package com.smartretails.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartretails.backend.config.ApiResponse;
import com.smartretails.backend.dto.StockBatchDto;
import com.smartretails.backend.entity.StockBatch;
import com.smartretails.backend.mapper.DtoMapper;
import com.smartretails.backend.service.InventoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryRepository;

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<List<StockBatchDto>>> getStock(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(ApiResponse.success(
                DtoMapper.mapList(inventoryRepository.geStockBatchs(productId), DtoMapper::toStockBatchDto)));
    }

    @PostMapping("/batch")
    public ResponseEntity<ApiResponse<StockBatchDto>> addBatch(@Valid @RequestBody StockBatch batch) {
        return ResponseEntity.ok(ApiResponse.success("Batch saved",
                DtoMapper.toStockBatchDto(inventoryRepository.addBatch(batch))));
    }

}
