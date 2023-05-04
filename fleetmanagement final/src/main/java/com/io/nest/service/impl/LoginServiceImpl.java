package com.io.nest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.nest.exception.UserNotFoundException;
import com.io.nest.model.User;
import com.io.nest.repository.LoginRepository;
import com.io.nest.service.LoginService;



@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginRepository loginRepository;

	@Override
	public User addUser(User user) {
		
		return loginRepository.save(user);
	}

	@Override
	public User getUser(long userId) throws UserNotFoundException{
		if(!loginRepository.existsById(userId)) {
			throw new UserNotFoundException("User with that Id does not exists");
		}
		return loginRepository.findById(userId).get();
	}

	@Override
	public User getLogin(String email, String password) throws UserNotFoundException {
		List<User> us = loginRepository.findAll();
		
		for(User u: us) {
			if((u.getEmail()).equals(email) && (u.getPassword()).equals(password)){
				return u;
			}else {
		
			throw new UserNotFoundException("Email and Password  does not match");
			}
		}
		return null;
		
		
		
	}

//	@Override
//	public String getUserEmail(String email)  throws UserNotFoundException {
//		if(loginRepository.getUserByEmail(email) == null) {
//			throw new UserNotFoundException("User with that Id does not exists");
//		}
//		User n = loginRepository.getUserByEmail(email);
//		return n.getPassword();
//	}
	
}
