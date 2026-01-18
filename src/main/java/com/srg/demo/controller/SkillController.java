package com.srg.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srg.demo.entity.Skill;
import com.srg.demo.repository.SkillRepository;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

	@Autowired
	private SkillRepository repo;

	@GetMapping
	public List<Skill> getAllSkills() {
		return repo.findAll();
	}
 }
