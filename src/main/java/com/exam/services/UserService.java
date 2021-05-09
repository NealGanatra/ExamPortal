package com.exam.services;

import java.util.Set;



import com.exam.entities.User;
import com.exam.entities.UserRole;


public interface UserService {
	
	public User createUser(User user,Set<UserRole> userRoles) throws Exception;

	public User getUser(String username);

	public void deleteUser(Long userId);

	public User updateUser(User oldUser);

}
