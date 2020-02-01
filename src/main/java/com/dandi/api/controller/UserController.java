package com.dandi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dandi.api.entity.User;
import com.dandi.api.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(value="/save")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		
		userRepository.save(user);
		
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
}
