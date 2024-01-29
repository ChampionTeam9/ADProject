package com.ad.teamnine.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ad.teamnine.model.User;
import com.ad.teamnine.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository userRepo; 
	
	//check for login
	public boolean Login(User user) {
		return false;
	}
}
