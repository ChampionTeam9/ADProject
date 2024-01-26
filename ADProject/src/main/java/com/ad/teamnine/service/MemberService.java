package com.ad.teamnine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ad.teamnine.model.*;
import com.ad.teamnine.repository.*;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public void registerMember(Member member) {
    	String rawPassword = member.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        member.setPassword(encodedPassword);
        memberRepository.save(member);
        userRepository.save(member);
    }
}
