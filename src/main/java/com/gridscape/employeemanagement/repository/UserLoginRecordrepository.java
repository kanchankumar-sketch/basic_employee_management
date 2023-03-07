package com.gridscape.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.gridscape.employeemanagement.model.UserLoginRecord;

@Repository
public interface UserLoginRecordrepository extends JpaRepository<UserLoginRecord,Long>{

	@Query(value = "select timestamps,counts from UserLoginRecord")
	List<List<Long>> getData();
	
	@Procedure("update_or_insert")
	void insertLoginCountPerMinute(Long date_time);
}
