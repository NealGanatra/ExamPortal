package com.exam.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.exceptions.UserFoundException;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws UserFoundException {
		// TODO Auto-generated method stub
		
		User local=this.userRepository.findByUsername(user.getUsername());
		if(local!=null) {
			System.out.println("User already exists");
			throw new UserFoundException();
		}
		else {
			for(UserRole ur:userRoles) {
			roleRepository.save(ur.getRole());
		}
		user.getUserRoles().addAll(userRoles);
		local=this.userRepository.save(user);
		}
		return local;
	}


	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
	}


	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(userId);
	}


	@Override
	public User updateUser(User oldUser) {
		// TODO Auto-generated method stub
		return this.userRepository.save(oldUser);
	}
	

	
}
