package com.dsproject.usermanagementservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginDto {
    private String email;
    private String password;
    }


