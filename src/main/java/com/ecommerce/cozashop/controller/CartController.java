package com.ecommerce.cozashop.controller;

import com.ecommerce.cozashop.model.CartItem;
import com.ecommerce.cozashop.model.Product;
import com.ecommerce.cozashop.model.User;
import com.ecommerce.cozashop.service.CartItemService;
import com.ecommerce.cozashop.service.ProductItemService;
import com.ecommerce.cozashop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductItemService productItemService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/shoping-cart")
    public String show(Model model) {
        List<Product> list = new ArrayList<>();
        List<CartItem> cartList = cartItemService.getAllProductCartWithUser(1L);
        double total = 0;
        Product product;
        for (CartItem cart: cartList) {
            product = productService.getProduct(cart.getProduct().getId());
            total += productItemService.getOneProduct(product.getId()).getPrice() * cart.getQty();
            list.add(product);
        }
        String totalstr = String.valueOf(((double) Math.round(total * 100) / 100));
        model.addAttribute("product_list", productService.getAllProduct());
        model.addAttribute("product_item_list", productItemService.getProductItems());
        model.addAttribute("cart_item", cartList);
        model.addAttribute("cart_prod", list);
        model.addAttribute("total", totalstr);

        return "shoping-cart";
    }

    @GetMapping("/add-to-cart/{id}/{qty}")
    @ResponseBody
    public Integer addToCart(@PathVariable(name = "id") Long id,
                            @PathVariable(name = "qty") Integer qty,
                            Model model) {

        List<CartItem> list = cartItemService.getAllProductCartWithUser(1L);

        if (cartItemService.checkProductAlreadyExists(id)) {
            CartItem item = cartItemService.getOneCartByProduct(id);
            item.setQty(item.getQty() + qty);
            cartItemService.addToCart(item);
            return list.size();
        }
        Product product = new Product();
        User user = new User();
        CartItem cartItem = new CartItem();
        product.setId(id);
        user.setId(1L);
        cartItem.setQty(qty);
        cartItem.setProduct(product);
        cartItem.setUser(user);
        cartItemService.addToCart(cartItem);
        list = cartItemService.getAllProductCartWithUser(1L);
        return list.size();
    }
}
