package com.srg.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srg.demo.dto.LoginPasswordDTO;
import com.srg.demo.dto.OtpDTO;
import com.srg.demo.dto.OwnerRegisterDTO;
import com.srg.demo.dto.ResetPasswordDTO;
import com.srg.demo.entity.Owner;
import com.srg.demo.repository.OwnerRepository;
import com.srg.demo.util.OtpUtil;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository repo;

    @Autowired
    private EmailService emailService;

    /* REGISTER */
    public Owner register(OwnerRegisterDTO dto) {

        if (repo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        if (repo.findByMobile(dto.getMobile()).isPresent()) {
            throw new RuntimeException("Mobile already exists");
        }

        Owner o = new Owner();
        o.setName(dto.getName());
        o.setMobile(dto.getMobile());
        o.setEmail(dto.getEmail());
        o.setPassword(dto.getPassword());
        o.setCity(dto.getCity());
        o.setArea(dto.getArea());

        return repo.save(o);
    }


    /* PASSWORD LOGIN */
    public Owner loginWithPassword(LoginPasswordDTO dto) {

        Owner owner = repo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not registered"));

        if (!owner.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        return owner;
    }

    /* SEND OTP */
    public void sendOtp(String email) {

        Owner owner = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not registered"));

        String otp = OtpUtil.generateOtp();

        owner.setOtp(otp);
        owner.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
        repo.save(owner);

        emailService.sendOtp(email, otp);
    }


    /* VERIFY OTP LOGIN */
    public Owner verifyOtp(OtpDTO dto) {

        Owner o = repo.findByEmail(dto.getEmail()).orElse(null);

        if (o == null) return null;
        if (o.getOtp() == null) return null;
        if (!o.getOtp().equals(dto.getOtp())) return null;
        if (o.getOtpExpiry().isBefore(LocalDateTime.now())) return null;

        o.setOtp(null);
        o.setOtpExpiry(null);
        repo.save(o);

        return o;
    }

    /* RESET PASSWORD */
    public void resetPassword(ResetPasswordDTO dto) {
        Owner o = repo.findByEmail(dto.getEmail()).orElseThrow();

        if (o.getOtp() != null && o.getOtp().equals(dto.getOtp())) {
            o.setPassword(dto.getNewPassword());
            o.setOtp(null);
            repo.save(o);
        }
    }
}
