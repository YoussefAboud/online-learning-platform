package com.dsproject.usermanagementservice.dto;

import jakarta.persistence.OneToMany;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TCRDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String centerName;
    private String address;
    private String location;
}
