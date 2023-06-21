package com.example.assignment_java5.service;

import com.example.assignment_java5.entity.CartItem;

import java.util.List;
import java.util.UUID;

public interface CartItemService {
    List<CartItem> items();

    void add(CartItem cartItem);

    void delete(UUID id);

    CartItem getById(UUID id);

}
