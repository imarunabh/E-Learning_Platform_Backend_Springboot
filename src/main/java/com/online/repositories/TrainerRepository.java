package com.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.entities.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long>{

}
