package com.InvestmentSimulation.StudyProject.controller;

import com.InvestmentSimulation.StudyProject.model.User;
import com.InvestmentSimulation.StudyProject.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/users/register")
    public String registerUser(@RequestBody User user){
        userService.register(user);
        return "Successful " + user.getNome();
    }

    @PostMapping("/users/login")
    public String loginUser(@RequestBody User user){
        boolean authenticated = userService.login(user.getEmail(), user.getSenha());
        return authenticated ? "Login successful" : "Invalid credentials";
    }
}