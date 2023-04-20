package com.ecommerce.cozashop.controller;

import com.ecommerce.cozashop.model.*;
import com.ecommerce.cozashop.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductItemService productItemService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShopOrderService shopOrderService;

    @Autowired
    private OrderLineService orderLineService;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

    @GetMapping("/check-out")
    public String show(Model model) {
        User user = (User) session.getAttribute("user");

        user.setId(userService.getIdUserByEmail(user.getEmail()));
        List<CartItem> cartList = cartItemService.getAllProductCartWithUser(user.getId());

        double total = cartItemService.getTotal(cartList) + 15.00;
        double subTotal = cartItemService.getTotal(cartList);

        model.addAttribute("cart_item", cartList);
        model.addAttribute("total", total);
        model.addAttribute("subTotal", subTotal);
        model.addAttribute("countCart", cartList.size());

        return "check-out";
    }

    @PostMapping("/check-out")
    public String checkOut(@ModelAttribute CartItem item, @ModelAttribute Address fullAddress) {

        User user = (User) session.getAttribute("user");

        user.setId(userService.getIdUserByEmail(user.getEmail()));
        List<CartItem> cartList = cartItemService.getAllProductCartWithUser(user.getId());

        ShopOrder shopOrder = new ShopOrder(user, LocalDate.now(), fullAddress.toString(), cartItemService.getTotal(cartList), true);
        shopOrderService.createNewOrder(shopOrder);


        for (CartItem c : cartList) {
            OrderLine orderLine = new OrderLine();

            orderLine.setShopOrder(shopOrder);
            orderLine.setProductItem(c.getItem());
            orderLine.setQty(c.getQty());
            orderLine.setPrice(c.getQty() * c.getItem().getPrice());

            // Add cart item -> order line
            orderLineService.createOrderLine(orderLine);

            // Remove cart item
            cartItemService.removeCartItem(c);
            System.out.println("Success");
        }
        return "redirect:/shopping-cart";
    }

}
