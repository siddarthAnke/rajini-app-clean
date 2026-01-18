package com.srg.demo.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "labour")
public class Labour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labourId;

    private String name;
    private int age;

    @Column(unique = true, nullable = false)
    private String mobile;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String city;
    private String area;

    private String status; // ACTIVE / INACTIVE

    private String otp;
    private LocalDateTime otpExpiry;

    @ManyToMany
    @JoinTable(
        name = "labour_skill"
    )
    private Set<Skill> skills = new HashSet<>();

    @PrePersist
    public void init() {
        this.status = "INACTIVE";
    }

	public Long getLabourId() {
		return labourId;
	}

	public void setLabourId(Long labourId) {
		this.labourId = labourId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getOtpExpiry() {
		return otpExpiry;
	}

	public void setOtpExpiry(LocalDateTime otpExpiry) {
		this.otpExpiry = otpExpiry;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public Labour(Long labourId, String name, int age, String mobile, String email, String password, String city,
			String area, String status, String otp, LocalDateTime otpExpiry, Set<Skill> skills) {
		super();
		this.labourId = labourId;
		this.name = name;
		this.age = age;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.city = city;
		this.area = area;
		this.status = status;
		this.otp = otp;
		this.otpExpiry = otpExpiry;
		this.skills = skills;
	}

    // getters & setters
    
	public Labour() {
		
	}
    
    
}
