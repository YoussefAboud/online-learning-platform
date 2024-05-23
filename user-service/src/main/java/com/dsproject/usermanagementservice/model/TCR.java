package com.dsproject.usermanagementservice.model;


//test center representative model

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.SecureRandom;
import java.util.List;

@Entity
@Table(name = "tcr", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TCR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String centerName;
    private String email;
    private String password;
    private String address;
    private String location;
}
