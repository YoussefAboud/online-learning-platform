package com.dsproject.usermanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.dsproject.usermanagementservice.model.User;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findOneByEmailAndPassword(String email, String password);
    User findByEmail(String email);

}
