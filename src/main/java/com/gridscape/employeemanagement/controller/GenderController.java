package com.gridscape.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gridscape.employeemanagement.model.Employee;
import com.gridscape.employeemanagement.model.Gender;
import com.gridscape.employeemanagement.service.EmployeeService;
import com.gridscape.employeemanagement.service.GenderService;

@RestController
@RequestMapping("/gender")
public class GenderController {

	@Autowired
	private GenderService genderService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public Gender saveGender(@RequestBody Gender gender) {
		return this.genderService.saveGender(gender);
	}
	
	@GetMapping
	public List<Gender> getGenders() {
		return this.genderService.getAllGender();
	}
	
	@PutMapping
	public Gender updateGender(@RequestBody Gender gender) {
		return this.genderService.saveGender(gender);
	}
	
	
	@DeleteMapping("/{genderId}")
	public String deleteGender(@PathVariable("genderId")  Long genderId) {
		this.genderService.deleteById(genderId);
		return "Successfully deleted";
	}
	
	@GetMapping("/{genderName}/employees")
	public List<Employee> getAllEmployeeByGenderName(@PathVariable("genderName") String genderName){
		return this.employeeService.getEmoloyeeByGender(genderName);
	}
}
