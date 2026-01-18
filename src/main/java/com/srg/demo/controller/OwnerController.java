package com.srg.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.srg.demo.dto.LoginPasswordDTO;
import com.srg.demo.dto.OtpDTO;
import com.srg.demo.dto.OwnerRegisterDTO;
import com.srg.demo.dto.ResetPasswordDTO;
import com.srg.demo.entity.Owner;
import com.srg.demo.service.OwnerService;

import java.util.Map;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    @Autowired
    private OwnerService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody OwnerRegisterDTO dto) {
        try {
            return ResponseEntity.ok(service.register(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

  
    @PostMapping("/login-password")
    public ResponseEntity<?> login(@RequestBody LoginPasswordDTO dto) {

        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty() ||
            dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }

        try {
            Owner owner = service.loginWithPassword(dto);
            return ResponseEntity.ok(owner);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    
    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> req) {

        String email = req.get("email");

        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }

        try {
            service.sendOtp(email);
            return ResponseEntity.ok("OTP sent successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpDTO dto) {

        if (dto.getEmail() == null || dto.getOtp() == null) {
            return ResponseEntity.badRequest().body("Email and OTP required");
        }

        Owner owner = service.verifyOtp(dto);

        if (owner == null) {
            return ResponseEntity.badRequest().body("Invalid or expired OTP");
        }

        return ResponseEntity.ok(owner);
    }

   
    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody ResetPasswordDTO dto) {
        service.resetPassword(dto);
    }
}
