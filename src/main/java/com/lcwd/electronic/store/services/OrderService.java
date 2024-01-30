package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.CreateOrderRequest;
import com.lcwd.electronic.store.dtos.OrderDto;
import com.lcwd.electronic.store.dtos.PageableResponse;

import java.util.List;

public interface OrderService {

    //create order



    //remove order

    OrderDto createOrder(CreateOrderRequest orderDto);

    void removeOrder(String orderId);

    //get orders of user
    List<OrderDto> getOrderOfUser(String userId);
    //get orders
    PageableResponse<OrderDto> getOrders(int pageNumber,int pageSize,String sortby,String dir);

}
