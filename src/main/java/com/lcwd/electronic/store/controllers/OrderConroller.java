package com.lcwd.electronic.store.controllers;


import com.lcwd.electronic.store.dtos.*;
import com.lcwd.electronic.store.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderConroller {

    @Autowired
    private OrderService orderService;
    //create order

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(
          @Valid @RequestBody CreateOrderRequest createOrderRequest
            ){
        OrderDto order=orderService.createOrder(createOrderRequest);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}")
    public  ResponseEntity<ApiResponseMessage> removeOrder(
          @PathVariable String orderId
    ){
        orderService.removeOrder(orderId);
        return new ResponseEntity<>(ApiResponseMessage.builder().message("order removed successfulyy").success(true).status(HttpStatus.OK).build(), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<OrderDto>> getOrderOfUser(
            @PathVariable String userId
    ){
        List<OrderDto> orderDtos=orderService.getOrderOfUser(userId);
        return new ResponseEntity<>(orderDtos,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<OrderDto>> getOrders(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "10", value = "pageSize", required = false) int pageSize,
            @RequestParam(defaultValue = "orderDate", value = "sortBy", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", value = "sortDir", required = false) String sortDir

    ) {
        PageableResponse<OrderDto> dtos = orderService.getOrders(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


}
