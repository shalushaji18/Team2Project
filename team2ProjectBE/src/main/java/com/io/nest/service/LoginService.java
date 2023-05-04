package com.io.nest.service;

import com.io.nest.exception.UserNotFoundException;
import com.io.nest.model.User;

public interface LoginService {

	public User addUser(User user) ;
	public User getUser(long userId) throws UserNotFoundException;
//	public User login(String email, String password);
	public User getLogin(String email, String password) throws UserNotFoundException;
//	public String getUserEmail(String e) throws UserNotFoundException;
		

}
