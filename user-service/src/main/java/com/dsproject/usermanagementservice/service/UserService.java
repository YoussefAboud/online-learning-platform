package com.dsproject.usermanagementservice.service;

import com.dsproject.usermanagementservice.dto.LoginDto;
import com.dsproject.usermanagementservice.dto.UserDto;
import com.dsproject.usermanagementservice.model.User;

import java.util.List;


public interface UserService {
    boolean register(User user);
    boolean loginUser(LoginDto loginDTO);
    List<User> listAll();
    void updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}


