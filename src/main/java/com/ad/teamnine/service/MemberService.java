package com.ad.teamnine.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ad.teamnine.model.Member;
import com.ad.teamnine.repository.MemberRepository;

//import com.ad.teamnine.model.*;
//import com.ad.teamnine.repository.*;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberRepository memberRepo;
	/*
	 * @Autowired private UserRepository userRepository;
	 */

	// update profile
	public void updateMyProfile(Member member, String gender, Double height, Double weight, LocalDate birthdate) {
		member.setGender(gender);
		member.setHeight(height);
		member.setWeight(weight);
		member.setBirthdate(birthdate);
		memberRepo.save(member);
	}

	// Register
	public void register(String username, String password, String confirmPassword, String gender, Double height,
			Double weight, LocalDate birthdate) {
		if (password == confirmPassword) {
			Member member = new Member();
			member.setUsername(username);
			member.setPassword(confirmPassword);
			member.setGender(gender);
			member.setHeight(height);
			member.setWeight(weight);
			member.setBirthdate(birthdate);
			memberRepo.save(member);
		}
	}



}
