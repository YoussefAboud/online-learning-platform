package com.dsproject.usermanagementservice.service;

import com.dsproject.usermanagementservice.dto.LoginDto;
import com.dsproject.usermanagementservice.dto.UserDto;
import com.dsproject.usermanagementservice.model.TCR;
import com.dsproject.usermanagementservice.model.User;
import com.dsproject.usermanagementservice.repository.TCRrepository;
import com.dsproject.usermanagementservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TCRrepository tcRrepository;

    @Override
    public boolean register(User user) {
        if ("admin".equals(user.getRole())) {
            System.out.println("Admin user cannot be registered.");
            return false;
        } else {
            userRepository.save(user);
            return true;
        }
    }


    @Override
    public boolean loginUser(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        TCR tcr = tcRrepository.findByEmail(loginDto.getEmail());
        if (user != null) {
            String storedPassword = user.getPassword();
            String providedPassword = loginDto.getPassword();
            return storedPassword.equals(providedPassword);
        }else if (tcr!= null){
            String storedPassword = tcr.getPassword();
            String providedPassword = loginDto.getPassword();
            return storedPassword.equals(providedPassword);
        }
        return false;
    }


    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(Long id, UserDto userDto){
        User user = userRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user Id:" + id));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setBio(userDto.getBio());
        user.setAffiliation(userDto.getAffiliation());
        user.setRole(userDto.getRole());
        user.setExperienceYears(userDto.getExperienceYears());

        userRepository.save(user);
    }


    @Override
    public void deleteUser(Long id) {
        User user = userRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user Id:" + id));

        userRepository.delete(user);
    }



}
