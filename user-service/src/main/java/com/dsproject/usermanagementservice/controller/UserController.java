package com.dsproject.usermanagementservice.controller;


import com.dsproject.usermanagementservice.dto.LoginDto;
import com.dsproject.usermanagementservice.dto.UserDto;
import com.dsproject.usermanagementservice.model.User;
import com.dsproject.usermanagementservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    //shaghal
    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        boolean registerd = userService.register(user);
        if (registerd) {
            return ResponseEntity.ok("User rigesterd successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Admin user cannot be registered.");
        }
    }

    //shaghal
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
        boolean loggedIn = userService.loginUser(loginDto);
        if (loggedIn) {
            return ResponseEntity.ok("User logged in successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed. Invalid credentials.");
        }
    }


    //shaghal
    @GetMapping("/list")
    public List<User> getUsers() {
        return userService.listAll();
    }


    //shaghala
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userService.updateUser(id, userDto);

        return ResponseEntity.noContent().build();
    }

    //shaghala
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}