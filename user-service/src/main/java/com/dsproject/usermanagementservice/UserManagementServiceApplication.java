package com.dsproject.usermanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class UserManagementServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(UserManagementServiceApplication.class, args);
    }
}