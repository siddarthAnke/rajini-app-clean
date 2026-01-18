package com.srg.demo.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srg.demo.dto.LabourRegisterDTO;
import com.srg.demo.dto.LoginPasswordDTO;
import com.srg.demo.dto.OtpDTO;
import com.srg.demo.dto.ResetPasswordDTO;
import com.srg.demo.entity.Labour;
import com.srg.demo.repository.LabourRepository;
import com.srg.demo.repository.SkillRepository;
import com.srg.demo.util.OtpUtil;

@Service
public class LabourService {

    @Autowired private LabourRepository labourRepo;
    @Autowired private SkillRepository skillRepo;
    @Autowired private EmailService emailService;

    /* REGISTER */
    public Labour register(LabourRegisterDTO dto) {

        if (labourRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        if (labourRepo.findByMobile(dto.getMobile()).isPresent()) {
            throw new RuntimeException("Mobile already exists");
        }

        Labour l = new Labour();
        l.setName(dto.getName());
        l.setAge(dto.getAge());
        l.setMobile(dto.getMobile());
        l.setEmail(dto.getEmail());
        l.setPassword(dto.getPassword());
        l.setCity(dto.getCity());
        l.setArea(dto.getArea());

        if (dto.getSkillIds() != null && !dto.getSkillIds().isEmpty()) {
            l.setSkills(new HashSet<>(skillRepo.findAllById(dto.getSkillIds())));
        } else {
            l.setSkills(new HashSet<>());
        }

        return labourRepo.save(l);
    }


    /* PASSWORD LOGIN */
    public Labour loginWithPassword(LoginPasswordDTO dto) {

        Labour labour = labourRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not registered"));

        if (!labour.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        return labour;
    }


    /* SEND OTP */
    public void sendOtp(String email) {

        Labour labour = labourRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not registered"));

        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        labour.setOtp(otp);
        labour.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
        labourRepo.save(labour);

        emailService.sendOtp(email, otp);
    }


    /* VERIFY OTP */
    public Labour verifyOtp(OtpDTO dto) {
        Labour l = labourRepo.findByEmail(dto.email).orElse(null);
        if (l != null &&
            l.getOtp().equals(dto.otp) &&
            l.getOtpExpiry().isAfter(LocalDateTime.now())) {
            l.setOtp(null);
            labourRepo.save(l);
            return l;
        }
        return null;
    }

    /* FORGOT PASSWORD */
    public void resetPassword(ResetPasswordDTO dto) {
        Labour l = labourRepo.findByEmail(dto.email).orElseThrow();
        if (l.getOtp().equals(dto.otp)) {
            l.setPassword(dto.newPassword);
            l.setOtp(null);
            labourRepo.save(l);
        }
    }

    /* STATUS */
    public void updateStatus(Long id, String status) {
        Labour l = labourRepo.findById(id).orElseThrow();
        l.setStatus(status);
        labourRepo.save(l);
    }

    /* OWNER SEARCH */
    public List<Labour> search(String city, String area, List<Long> skills) {

        city = city.trim();
        area = area.trim();

        System.out.println("SEARCH city = [" + city + "]");
        System.out.println("SEARCH area = [" + area + "]");
        System.out.println("SEARCH skills = " + skills);

        return labourRepo.searchActiveLabours(city, area, skills);
    }

}
