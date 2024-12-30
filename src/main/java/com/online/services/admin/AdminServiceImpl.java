package com.online.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.online.entities.Admin;
import com.online.entities.User;
import com.online.enums.UserRole;
import com.online.repositories.AdminRepository;
import com.online.repositories.UserRepository;

import jakarta.annotation.PostConstruct;



public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
//	@PostConstruct
	public void createAdmin() {
		User user= new User();
		user.setEmail("admin@test.com");
		user.setPassword(new BCryptPasswordEncoder().encode("admin"));
		user.setRole(UserRole.ADMIN);
		userRepository.save(user);
		
		Admin admin = new Admin();
		admin.setAddress("AdminAddress");
		admin.setBloodGroup("A+");
		admin.setEmail("admin@test1.com");
		admin.setName("Admin");
		admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
		admin.setPost_School("Admin");
		adminRepository.save(admin);
	}
	

}
