package com.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
