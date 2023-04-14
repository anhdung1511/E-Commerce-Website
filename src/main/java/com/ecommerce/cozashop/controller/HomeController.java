package com.ecommerce.cozashop.controller;


import com.ecommerce.cozashop.model.CartItem;
import com.ecommerce.cozashop.model.ImageProduct;
import com.ecommerce.cozashop.model.Product;
import com.ecommerce.cozashop.model.ProductItem;
import com.ecommerce.cozashop.service.CartItemService;
import com.ecommerce.cozashop.service.ImageProductService;
import com.ecommerce.cozashop.service.ProductItemService;
import com.ecommerce.cozashop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductItemService productItemService;

    @Autowired
    private CartItemService cartItemService;



    @GetMapping("/home")
    public String showHome(Model model) {
        int totalCart = cartItemService.getAllProductCartWithUser(1L).size();

        model.addAttribute("product_list", productService.getAllProduct());
        model.addAttribute("product_item_list", productItemService.getProductItems());
        model.addAttribute("totalCart", totalCart);
        return "index";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }

    @GetMapping("/contact")
    public String showContact() {return "contact";}
}
