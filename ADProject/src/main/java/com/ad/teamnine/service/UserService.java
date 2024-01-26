package com.ad.teamnine.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ad.teamnine.model.*;
import com.ad.teamnine.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository userRepo; 
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean authenticateUser(String username, String password) {
        User user = userRepo.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Authentication successful
            return true;
        } 
        else {
            // Authentication failed
            return false;
        }
    }
}
