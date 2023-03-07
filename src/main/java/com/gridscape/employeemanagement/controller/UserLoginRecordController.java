package com.gridscape.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gridscape.employeemanagement.model.UserLoginRecord;
import com.gridscape.employeemanagement.service.UserLoginRecordService;

@RestController
@RequestMapping("/loginRecord")
public class UserLoginRecordController {

	
	@Autowired
	private UserLoginRecordService loginRecordService;
	
	@GetMapping
	public List<UserLoginRecord> getRecords(){
		return this.loginRecordService.getrecord();
	}
	
	@GetMapping("/data")
	public List<List<Long>> getData(){
		return this.loginRecordService.getAllData();
	}
}
