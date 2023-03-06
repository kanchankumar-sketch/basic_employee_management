package com.gridscape.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gridscape.employeemanagement.model.Employee;
import com.gridscape.employeemanagement.model.Gender;
import com.gridscape.employeemanagement.repository.GenderRepository;

@Service
public class GenderService {

	@Autowired
	private GenderRepository genderRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	public Gender saveGender(Gender gender) {
		return this.genderRepository.save(gender);
	}
	
	public Gender getGenderById(Long id) {
		return this.genderRepository.findById(id).orElse(null);
	}

	public List<Gender> getAllGender() {
		return this.genderRepository.findAll();
	}

	public void deleteById(Long genderId) {
		this.genderRepository.deleteById(genderId);
		
	}

	/*
	 * public List<Employee> getEmoloyeeByGender(String genderName) { Gender gender=
	 * this.genderRepository.findByName(genderName).orElseThrow(); return
	 * this.employeeService.getAllEmployeesByGender(gender.getId()); }
	 */

	
}
