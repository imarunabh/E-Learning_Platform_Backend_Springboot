package com.online.services.student;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.online.entities.Student;
import com.online.entities.User;
import com.online.enums.UserRole;
import com.online.repositories.StudentRepository;
import com.online.repositories.UserRepository;

import jakarta.annotation.PostConstruct;



public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
//	@PostConstruct
	public void createStudent() {
		User user = new User();
		user.setEmail("student@test.com");
		user.setPassword(new BCryptPasswordEncoder().encode("student"));
		user.setRole(UserRole.STUDENT);
		userRepository.save(user);
		
		Student student = new Student();
		student.setAddress("StudentAddress");
		student.setBloodGroup("A+");
		student.setEmail("student@test.com");
		student.setName("Student");
		student.setPassword(new BCryptPasswordEncoder().encode("student"));
		student.setAge(20);
		student.setDob(new Date());
		student.setFatherName("StudentFather");
		student.setMotherName("StudentMother");
		student.setS_Class("5");
		studentRepository.save(student);
	}

}
