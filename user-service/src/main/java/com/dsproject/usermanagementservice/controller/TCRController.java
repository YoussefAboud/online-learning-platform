package com.dsproject.usermanagementservice.controller;

import com.dsproject.usermanagementservice.dto.TCRDto;
import com.dsproject.usermanagementservice.service.TestCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tcr")
public class TCRController {

    @Autowired
    private TestCenterService testCenterService;



    //shaghal
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody TCRDto tcrDto){
        boolean registerd = testCenterService.register(tcrDto);
        if (registerd) {
            return ResponseEntity.ok("User rigesterd successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Admin user cannot be registered.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTCR(@PathVariable long id, @RequestBody TCRDto tcrDto) {
            testCenterService.updateTCR(id, tcrDto);
            return ResponseEntity.noContent().build();

    }
}
