package com.ecommerce.cozashop.controller;

import com.ecommerce.cozashop.model.Role;
import com.ecommerce.cozashop.model.User;
import com.ecommerce.cozashop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String showLogin() {
        return "account/login";
    }

    @PostMapping("/login")
    public String loginAccount(@ModelAttribute User user,
                               Model model) {
        try {
            if (userService.checkEmailAlreadyExists(user.getEmail())) {
                model.addAttribute("error", "Wrong email address, please re-enter your email!");
                return "account/login";
            } else {
                Base64.Decoder decoder = Base64.getDecoder();
                String password_decoder = new String(decoder.decode(userService.getPasswordByEmail(user.getEmail())));
                if (!user.getPassword().equals(password_decoder)) {
                    model.addAttribute("error", "Wrong password, please re-enter password!");
                    return "account/login";
                }
                return "redirect:/home";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "account/login";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "account/register";
    }

    @PostMapping("/register")
    public String registerAccount(@ModelAttribute User user,
                                  Model model) {

        try {
            if (!userService.checkEmailAlreadyExists(user.getEmail()) ) {
                model.addAttribute("error", "Email address already exists");
                return "acount/register";
            } else if (!userService.checkPhoneAlreadyExists(user.getPhone())){
                model.addAttribute("error", "Phone number address already exists");
                return "acount/register";
            } else {
                Role role = new Role();
                Base64.Encoder encoder = Base64.getEncoder();
                String encodeStr = encoder.encodeToString(user.getPassword().getBytes(StandardCharsets.UTF_8));

                user.setPassword(encodeStr);
                role.setId(3);
                user.setRole(role);
                user.setStatus(true);

                userService.registerAccount(user);
                return "redirect:acount/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error 500");
        }
        return "acount/register";
    }


    @GetMapping("/forgot-password")
    public String showForgotPassword() {
        return "account/forgot-password";
    }
}
