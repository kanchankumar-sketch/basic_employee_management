package com.gridscape.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gridscape.employeemanagement.model.UserLoginRecord;
import com.gridscape.employeemanagement.repository.UserLoginRecordrepository;

@Service
public class UserLoginRecordService {

	@Autowired
	private UserLoginRecordrepository loginRecordrepository;
	
	public UserLoginRecord  saveRecord(UserLoginRecord loginRecord) {
		return this.loginRecordrepository.save(loginRecord);
	}
	
	public List<UserLoginRecord> getrecordbyEmail(String email){
		return this.loginRecordrepository.findByEmail(email);
	}
}
