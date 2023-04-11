package com.ecommerce.cozashop.controller;

import com.ecommerce.cozashop.model.CartItem;
import com.ecommerce.cozashop.model.Product;
import com.ecommerce.cozashop.service.CartItemService;
import com.ecommerce.cozashop.service.ProductItemService;
import com.ecommerce.cozashop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
