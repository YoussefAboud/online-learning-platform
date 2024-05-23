package com.dsproject.usermanagementservice.service;

import com.dsproject.usermanagementservice.dto.TCRDto;
import com.dsproject.usermanagementservice.model.TCR;
import com.dsproject.usermanagementservice.repository.TCRrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;

@Service
public class TestCenterService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 10;

    @Autowired
    private TCRrepository tcrRepository;


    public String generateRandomPassword() {
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }

        return password.toString();
    }


    public boolean register(TCRDto tcrDto) {

        String password = generateRandomPassword();

        TCR tcr = new TCR();
        tcr.setName(tcrDto.getName());
        tcr.setCenterName(tcrDto.getCenterName());
        tcr.setEmail(tcrDto.getEmail());
        tcr.setPassword(password);
        // Check if the center name is already taken
        if (tcrRepository.existsByCenterName(tcr.getCenterName())) {
            System.out.println("Center name already exists.");
            return false;
        }else {
            tcrRepository.save(tcr);
            return true;
        }

    }

    public void updateTCR(long id, TCRDto tcrDto) {


        TCR tcr = tcrRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid tcr Id:" + id));

        tcr.setName(tcrDto.getName());
        tcr.setCenterName(tcrDto.getCenterName());
        tcr.setEmail(tcrDto.getEmail());
        tcr.setLocation(tcrDto.getLocation());
        tcr.setAddress(tcr.getAddress());

        tcrRepository.save(tcr);
    }




}
