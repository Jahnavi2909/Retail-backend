package com.smartretails.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartretails.backend.config.ApiResponse;
import com.smartretails.backend.dto.PurchaseOrderDto;
import com.smartretails.backend.entity.PurchaseOrder;
import com.smartretails.backend.mapper.DtoMapper;
import com.smartretails.backend.service.PurchaseOrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/purchase-orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PurchaseOrderDto>>> getAllOrders() {
        return ResponseEntity.ok(ApiResponse.success(
                DtoMapper.mapList(purchaseOrderRepository.getAllOrders(), DtoMapper::toPurchaseOrderDto)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PurchaseOrderDto>> createOrder(@Valid @RequestBody PurchaseOrder purchaseOrder) {
        return ResponseEntity.ok(ApiResponse.success("Purchase order saved",
                DtoMapper.toPurchaseOrderDto(purchaseOrderRepository.createPurchaseOrder(purchaseOrder))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PurchaseOrderDto>> getOrderById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(
                DtoMapper.toPurchaseOrderDto(purchaseOrderRepository.getOrderById(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PurchaseOrderDto>> updateOrder(@PathVariable("id") Long id,
            @Valid @RequestBody PurchaseOrder purchaseOrder) {
        return ResponseEntity.ok(ApiResponse.success(
                DtoMapper.toPurchaseOrderDto(purchaseOrderRepository.updateOrder(id, purchaseOrder))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteOrder(@PathVariable("id") Long id) {
        purchaseOrderRepository.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
