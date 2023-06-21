package com.example.assignment_java5.service.impl;

import com.example.assignment_java5.entity.CartItem;
import com.example.assignment_java5.service.CartItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartItemServiceImpl implements CartItemService {
    private List<CartItem> listItem = new ArrayList<>();

    @Override
    public List<CartItem> items() {
        return listItem;
    }

    @Override
    public void add(CartItem cartItem) {

        for (CartItem i : listItem) {
            if (i.getIdCTSP().equals((cartItem.getIdCTSP()))) {
                i.setSoLuong(i.getSoLuong() + 1);
                return;
            }
        }
        listItem.add(cartItem);
    }

    @Override
    public CartItem getById(UUID id) {
        for (CartItem x : listItem) {
            if (id.equals(x.getIdCTSP())) {
                return x;
            }
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        CartItem cartItem = getById(id);
        listItem.remove(cartItem);
    }
}
