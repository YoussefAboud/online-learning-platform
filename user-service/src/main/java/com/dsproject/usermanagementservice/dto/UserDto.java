package com.dsproject.usermanagementservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private String password;
    private String affiliation;
    private String bio;
    private String role;
    private int experienceYears;
}

