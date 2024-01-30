package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.dtos.AddItemToCartRequest;
import com.lcwd.electronic.store.dtos.CartDto;
import com.lcwd.electronic.store.dtos.CartItemDto;
import com.lcwd.electronic.store.entities.Cart;
import com.lcwd.electronic.store.entities.CartItem;
import com.lcwd.electronic.store.entities.Product;
import com.lcwd.electronic.store.entities.User;
import com.lcwd.electronic.store.exceptions.BadApiRequestException;
import com.lcwd.electronic.store.exceptions.ResourceNotFoundException;
import com.lcwd.electronic.store.repositories.CartItemRepository;
import com.lcwd.electronic.store.repositories.CartRepository;
import com.lcwd.electronic.store.repositories.ProductRepository;
import com.lcwd.electronic.store.repositories.UserRepository;
import com.lcwd.electronic.store.services.CartService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {

       // String random= UUID.randomUUID().toString();

        int quantity = request.getQuantity();
        String productId = request.getProductId();
        if (quantity <= 0) {
            throw new BadApiRequestException("Requested quantity is not valid !!");
        }

        //fetch product

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found with given Id!!!"));

        //fetch user from db

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given Id!!!"));

        Cart cart = null;


        try {
            cart = cartRepository.findByUser(user).get();

        } catch (NoSuchElementException ex) {
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedAt(new Date());
        }

        //if cart item already present then update or update

        AtomicReference<Boolean> updated = new AtomicReference<>(false);
        List<CartItem> itemList = cart.getItems();

        itemList = itemList.stream().map(item -> {
            if (item.getProduct().getProductId().equals(productId)) {
                //item already present in cart

                item.setQuantity(quantity);
                item.setTotalPrice(quantity * product.getDiscountedPrice());
                updated.set(true);
            }
            return item;

        }).collect(Collectors.toList());

        //cart.setItems(updateditems);
        if (!updated.get()) {
            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .totalPrice(quantity * product.getDiscountedPrice())
                    .quantity(quantity)
                    .product(product)
                    .build();
            cart.getItems().add(cartItem);
        }

        cart.setUser(user);

        Cart updatedCart = cartRepository.save(cart);

        return mapper.map(updatedCart, CartDto.class);
    }

    @Override
    public void removeItemFromCart(String userId, int cartItemId) {

        var cartItem1 = cartItemRepository.findById(cartItemId).orElseThrow(() -> new ResourceNotFoundException("Cart not found with given Id!!!"));
        cartItemRepository.delete(cartItem1);
    }

    @Override
    public void clearCart(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given Id!!!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("cart not found for given user!!!"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }
    @Override
    public CartDto getCartByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given Id!!!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("cart not found for given user!!!"));
        return mapper.map(cart, CartDto.class);
    }
}
