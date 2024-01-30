package com.ad.teamnine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ad.teamnine.model.Member;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/register")
	public String register(Model model)
	{model.addAttribute("member", new Member());
		return "register";
	}
	
	@PostMapping("/register")
	public String registermember(
			@Valid 
			@ModelAttribute("member") Member inMember,
			BindingResult bindingResult
			)
	{
		if(bindingResult.hasErrors()) {
			return"register";
		}
		
		
		return "test";
	}
}
