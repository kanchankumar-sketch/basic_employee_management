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
import com.gridscape.employeemanagement.service.EmployeeService;
import com.gridscape.employeemanagement.service.EncryptionService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public Employee registerEmployee(@RequestBody Employee employee) {
		employee.setPassword(EncryptionService.cryptWithMD5(employee.getPassword()));
		return this.employeeService.saveEmployee(employee);
	}
	
	@PutMapping
	public Employee updateEmployeeDetails(@RequestBody Employee employee) {
		return this.employeeService.saveEmployee(employee);
	}
	
	@GetMapping
	public List<Employee> getAllEmplloyees(){
		return this.employeeService.getAllEmployees();
	}
	
	@DeleteMapping("/{employeeId}")
	public String deleteEmployee(@PathVariable("employeeId") Long employeeId) {
		this.employeeService.deleteById(employeeId);
		return "deleted successfully";
	}
	
	@GetMapping("/exists/{email}")
	public Boolean findEmployee(@PathVariable("email") String email) {
		return this.employeeService.getEmployeeDetailsByEmail(email);
	}
}
