package com.example.ComplainServiceApp.services;

import com.example.ComplainServiceApp.entity.User;
import com.example.ComplainServiceApp.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserSevice {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepositry userRepositry;

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepositry.save(user);
    }
    public void saveUser(User user){
        userRepositry.save(user);
    }
    public List<User> getAll(){
        return userRepositry.findAll();
    }
}
