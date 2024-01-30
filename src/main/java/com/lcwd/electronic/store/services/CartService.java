package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.AddItemToCartRequest;
import com.lcwd.electronic.store.dtos.CartDto;

public interface CartService {
    //add items to cart:
    //cart  for user is not available :we will create
    //if cart available we will update cart

    CartDto addItemToCart(String userId, AddItemToCartRequest request);

    //remove Item from cart
    void removeItemFromCart(String userId,int cartItem);

    //remove all items from cart
    void clearCart(String userId);
    CartDto getCartByUser(String userId);
}
