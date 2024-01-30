package com.lcwd.electronic.store.dtos;

import com.lcwd.electronic.store.entities.OrderItem;
import com.lcwd.electronic.store.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private String ordeId;

    //pending,dispatch
    //enum
    private String orderStatus="PENDING";

    //Not paid,paid
    //enum
    private String paymentStatus="NOTPAID";

    private int orderAmount;


    private String billingAddress;

    private String billingPhone;

    private String billingName;


    private Date orderDate=new Date();

    private Date deliverDate;

    //user


    //private UserDto user;

     private List<OrderItemDto> orderItems=new ArrayList<>();
}
