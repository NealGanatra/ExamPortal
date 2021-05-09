package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.entities.Role;
import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.services.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;
	public static void main(String[] args) throws Exception,ArithmeticException{
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception,ArithmeticException {
		// TODO Auto-generated method stub
		/*
		 * System.out.println("Starting code"); User user=new User();
		 * user.setFirstName("ABC"); user.setLastName("T"); user.setUsername("neelg83");
		 * user.setPassword("test"); user.setPhone("99999999");
		 * user.setEmail("neel7@gmail.com"); user.setProfile("default.png");
		 * 
		 * Role role =new Role(); //role.setRoleId(45L); role.setRoleName("NORMAL");
		 * 
		 * 
		 * Set<UserRole> userRoles=new HashSet<>(); UserRole ur=new UserRole();
		 * 
		 * ur.setRole(role); ur.setUser(user); userRoles.add(ur);
		 * 
		 * User user1=this.userService.createUser(user, userRoles);
		 * System.out.println(user1);
		 */
	}

}
