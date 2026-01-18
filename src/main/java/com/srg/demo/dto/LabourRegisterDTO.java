package com.srg.demo.dto;

import java.util.List;

public class LabourRegisterDTO {
    public String name;
    public int age;
    public String mobile;
    public String email;
    public String password;
    public String city;
    public String area;
    public List<Long> skillIds;
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
	public List<Long> getSkillIds() {
		return skillIds;
	}
	public void setSkillIds(List<Long> skillIds) {
		this.skillIds = skillIds;
	}
	public LabourRegisterDTO(String name, int age, String mobile, String email, String password, String city,
			String area, List<Long> skillIds) {
		super();
		this.name = name;
		this.age = age;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.city = city;
		this.area = area;
		this.skillIds = skillIds;
	}
    
	public LabourRegisterDTO() {
		
	}

}
