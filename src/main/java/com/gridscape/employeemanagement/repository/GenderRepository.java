package com.gridscape.employeemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gridscape.employeemanagement.model.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender,Long>{

//	Optional<Gender> findByName(String genderName);

}
