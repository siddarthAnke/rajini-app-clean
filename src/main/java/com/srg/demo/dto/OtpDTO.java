package com.srg.demo.dto;

public class OtpDTO {
    public String email;
    public String otp;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public OtpDTO(String email, String otp) {
		super();
		this.email = email;
		this.otp = otp;
	}
    
	public OtpDTO() {
		
	}
    
}
