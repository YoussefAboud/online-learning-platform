package com.dsproject.usermanagementservice.repository;

import com.dsproject.usermanagementservice.model.TCR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface TCRrepository extends JpaRepository<TCR, Long> {
    boolean existsByCenterName(String centerName);
    TCR findByEmail(String email);
}
