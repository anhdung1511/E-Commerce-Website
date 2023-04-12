package com.ecommerce.cozashop.service;

import com.ecommerce.cozashop.model.CartItem;
import com.ecommerce.cozashop.repository.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepo cartItemRepo;

    public List<CartItem> getAllProductCartWithUser(Long id) {
        return cartItemRepo.findCart(id);
    }

    public List<CartItem> getAllProductCart (){
        return  cartItemRepo.findAll();
    }

    public Integer getQtyProduct(Long id) {
        return cartItemRepo.getQtyCart(id);
    }

    public boolean checkProductAlreadyExists(Long id) {
        return cartItemRepo.checkCartItem(id) == null ? false : true;
    }

    public CartItem getOneCartByProduct(Long id) {
        return cartItemRepo.checkCartItem(id);
    }

    public void addToCart(CartItem item) {
        cartItemRepo.save(item);
    }

}
