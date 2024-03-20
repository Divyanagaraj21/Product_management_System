package com.jsp.spring.pmsm.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.spring.pmsm.model.User;
import com.jsp.spring.pmsm.repository.UserRepository;
import com.jsp.spring.pmsm.service.UserService;
import com.jsp.spring.pmsm.utility.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private ResponseStructure<User> structure;

	public UserServiceImpl(UserRepository userRepository,ResponseStructure<User> structure) {
		super();
		this.userRepository = userRepository;
		this.structure=structure;
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		User uniqueUser=userRepository.save(user);
		return ResponseEntity.ok(structure.setStatuscode(HttpStatus.OK.value())
				.setMessage("data saved successfully")
				.setData(uniqueUser));
	}
}
