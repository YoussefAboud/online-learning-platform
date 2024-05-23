package com.dsproject.usermanagementservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames = "email") )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;
 private String name;
 private String email;
 private String password;
 private String affiliation;
 private String bio;
 private String role;
 private int experienceYears;
}
