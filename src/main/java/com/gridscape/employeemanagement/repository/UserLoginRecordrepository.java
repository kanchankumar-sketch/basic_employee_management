package com.gridscape.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gridscape.employeemanagement.model.UserLoginRecord;

@Repository
public interface UserLoginRecordrepository extends JpaRepository<UserLoginRecord,Long>{

	List<UserLoginRecord> findByEmail(String email);

}
