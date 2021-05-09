package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entities.Role;
import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
@Autowired
private UserService userService;	

@RequestMapping(value="/",method = RequestMethod.POST)
public User createUser(@RequestBody User user) throws Exception{
	Set<UserRole> roles=new HashSet<>();
	Role role=new Role();
	//role.setRoleId(49L);
	user.setProfile("default.png");
	role.setRoleName("NORMAL");
	UserRole ur=new UserRole();
	ur.setUser(user);
	ur.setRole(role);
	
	
	roles.add(ur);
	
	
	
	

		return this.userService.createUser(user, roles);
	
}

@GetMapping("/{username}")
public User getUser(@PathVariable("username")String username) {
	
	return this.userService.getUser(username);
}

@DeleteMapping("/{userId}")
public void deleteUser(@PathVariable("userId") Long userId) {
	this.userService.deleteUser(userId);
}

@PutMapping("/{username}")
public User updateUser(@RequestBody User user,@PathVariable("username")String username) {
	User oldUser=this.userService.getUser(username);
oldUser.setEmail(user.getEmail());
oldUser.setFirstName(user.getFirstName());
oldUser.setLastName(user.getLastName());
oldUser.setPassword(user.getPassword());
oldUser.setPhone(user.getPhone());
oldUser.setProfile(user.getProfile());

return this.userService.updateUser(oldUser);
}
}
