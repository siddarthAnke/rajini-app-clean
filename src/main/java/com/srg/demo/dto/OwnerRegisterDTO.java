package com.srg.demo.dto;

public class OwnerRegisterDTO {
    public String name;
    public String mobile;
    public String email;
    public String password;
    public String city;
    public String area;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public OwnerRegisterDTO(String name, String mobile, String email, String password, String city, String area) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.city = city;
		this.area = area;
	}
	public OwnerRegisterDTO() {
		
	}
    
}
