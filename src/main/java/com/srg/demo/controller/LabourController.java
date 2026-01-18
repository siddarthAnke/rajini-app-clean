package com.srg.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srg.demo.dto.LabourRegisterDTO;
import com.srg.demo.dto.LoginPasswordDTO;
import com.srg.demo.dto.OtpDTO;
import com.srg.demo.dto.ResetPasswordDTO;
import com.srg.demo.entity.Labour;
import com.srg.demo.service.LabourService;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/labour")
public class LabourController {

    @Autowired private LabourService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LabourRegisterDTO dto) {
        try {
            return ResponseEntity.ok(service.register(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login-password")
    public ResponseEntity<?> loginPassword(@RequestBody LoginPasswordDTO dto) {

        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty() ||
            dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }

        try {
            Labour labour = service.loginWithPassword(dto);
            return ResponseEntity.ok(labour);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> req) {

        String email = req.get("email");

        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Email is required");
        }

        try {
            service.sendOtp(email);
            return ResponseEntity.ok("OTP sent successfully");
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    @PostMapping("/verify-otp")
    public Labour verifyOtp(@RequestBody OtpDTO dto) {
        return service.verifyOtp(dto);
    }

    @PostMapping("/reset-password")
    public void reset(@RequestBody ResetPasswordDTO dto) {
        service.resetPassword(dto);
    }

    @PutMapping("/{id}/status")
    public void status(@PathVariable Long id, @RequestParam String status) {
        service.updateStatus(id, status);
    }

    @PostMapping("/search")
    public List<Labour> search(
        @RequestParam String city,
        @RequestParam String area,
        @RequestBody List<Long> skills) {

        return service.search(city, area, skills);
    }
}
