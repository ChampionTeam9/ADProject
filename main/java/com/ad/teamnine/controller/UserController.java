package com.ad.teamnine.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ad.teamnine.model.Member;
import com.ad.teamnine.repository.MemberRepository;
import com.ad.teamnine.repository.UserRepository;
import com.ad.teamnine.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userSer;
	@Autowired
	UserRepository userre;
	@Autowired
	MemberRepository memberre;

	@GetMapping("/setPreference")
	public String setPerference(Model model) {
		Set<String> tags = userSer.getRandomUniqueTags(7);
		model.addAttribute("tags", tags);
		return "setPreference";
	}

	@PostMapping("/setPreference")
	public String receivePreference(@RequestParam(value = "tags", required = false) List<String> tags,HttpSession session) {
		List<String> oldTags = (List<String>) session.getAttribute("tags");
		Member member=new Member();
		if (oldTags == null) {
			member.setPrefenceList(tags);
			memberre.save(member);
		} else {
			Set<String> selectedTags = new HashSet<>(oldTags);
			selectedTags.addAll(tags);
			List<String> combinedTags = new ArrayList<>(selectedTags);
			member.setPrefenceList(combinedTags);
			memberre.save(member);
		}
		
		
		return "test";
	}

	@PostMapping("/refresh")
	public String refreshTags(Model model, @RequestParam("tags") List<String> tags, HttpSession session) {
		List<String> oldTags = (List<String>) session.getAttribute("tags");
		if (oldTags == null) {
			session.setAttribute("tags", tags);
		} else {
			Set<String> selectedTags = new HashSet<>(oldTags);
			selectedTags.addAll(tags);
			List<String> combinedTags = new ArrayList<>(selectedTags);
			session.setAttribute("tags", combinedTags);
			Set<String> newTags = userSer.getRandomUniqueTags(7);
			model.addAttribute("tags", newTags);
		}
		Set<String> newTags = userSer.getRandomUniqueTags(7);
		model.addAttribute("tags", newTags);
		return "redirect:/user/setPreference";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("member", new Member());
		return "register";
	}

	@PostMapping("/register")
	public String registermember(@Valid @ModelAttribute("member") Member inMember, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
		LocalDate birthdate = inMember.getBirthdate();
		LocalDate currentDate = LocalDate.now();
		if (birthdate != null) {
			int age = Period.between(birthdate, currentDate).getYears();
			inMember.setAge(age);
		}
		memberre.save(inMember);
		return "page1";
	}

}
