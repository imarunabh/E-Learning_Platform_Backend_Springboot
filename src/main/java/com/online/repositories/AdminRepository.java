package com.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

}
