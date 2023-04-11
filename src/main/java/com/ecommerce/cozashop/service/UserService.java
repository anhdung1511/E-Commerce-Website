package com.ecommerce.cozashop.service;

import com.ecommerce.cozashop.model.User;
import com.ecommerce.cozashop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public boolean checkEmailAlreadyExists(String email) {
        return userRepo.findByEmail(email) == null ? true : false;
    }

    public void registerAccount(User user) {
        userRepo.save(user);
    }

    public String getPasswordByEmail(String email) {
        return userRepo.getPassword(email);
    }

    public List<User> show() {
        return userRepo.findAll();
    }

    public boolean checkPhoneAlreadyExists(String phone) {
        return userRepo.findByPhone(phone) == null ? true : false;
    }
}
