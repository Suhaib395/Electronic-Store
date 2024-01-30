package com.lcwd.electronic.store.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequest {
    @NotBlank(message = "Cart id required ")
    private String cartId;
    @NotBlank(message = "user id required")
    private String userId;

    private String orderStatus = "PENDING";
    private String paymentStatus = "NOTPAID";
    private int orderAmount;
    @NotBlank(message = "address is required")
    private String billingAddress;
    @NotBlank(message = "phone is required")
    private String billingPhone;
    @NotBlank(message = "billing name is required")
    private String billingName;


    //user


    //private UserDto user;


}
