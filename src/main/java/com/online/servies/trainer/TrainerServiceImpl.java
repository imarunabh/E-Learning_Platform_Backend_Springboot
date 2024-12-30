package com.online.servies.trainer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.online.entities.Trainer;
import com.online.entities.User;
import com.online.enums.UserRole;
import com.online.repositories.TrainerRepository;
import com.online.repositories.UserRepository;

import jakarta.annotation.PostConstruct;


public class TrainerServiceImpl implements TrainerService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TrainerRepository trainerRepository;
	
//	@PostConstruct
	public void createStudent() {
		User user = new User();
		user.setEmail("teacher@test.com");
		user.setPassword(new BCryptPasswordEncoder().encode("teacher"));
		user.setRole(UserRole.TRAINER);
		userRepository.save(user);
		
		Trainer trainer = new Trainer();
		trainer.setAddress("TeacherAddress");
		trainer.setBloodGroup("A+");
		trainer.setEmail("teacher@test.com");
		trainer.setName("Teacher");
		trainer.setPassword(new BCryptPasswordEncoder().encode("teacher"));
		trainer.setAge(20);
		trainer.setDob(new Date());
		trainer.setSalary(100000L);
		trainerRepository.save(trainer);
	}

}
