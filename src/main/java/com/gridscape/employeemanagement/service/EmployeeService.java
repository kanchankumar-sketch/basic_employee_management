package com.gridscape.employeemanagement.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gridscape.employeemanagement.dto.LoginModel;
import com.gridscape.employeemanagement.model.Employee;
import com.gridscape.employeemanagement.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository  employeeRepository;
	
	
	public Employee saveEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}
	
	public Employee getEmployeeDetails(Long employeeId) {
		return this.employeeRepository.findById(employeeId).orElseThrow();
	}
	
	public List<Employee> getAllEmployees(){
		return this.employeeRepository.findAll();
	}
	

	public void deleteById(Long employeeId) {
		this.employeeRepository.deleteById(employeeId);
	}

//	public List<Employee> getAllEmployeesByGender(Long id) {
//		return this.employeeRepository.findByGenderId(id);
//	}

	public List<Employee> getEmoloyeeByGender(String genderName) {
		return this.employeeRepository.findByGender(genderName);
	}

	public Boolean getEmployeeDetailsByEmail(String email) {
		Optional<Employee> employee=this.employeeRepository.findByEmail(email);
		return employee.isPresent();
	}
	
	public Employee getEmployeeDetailsByEmail1(String email) {
		return this.employeeRepository.findByEmail(email).orElse(null);
	}
	
	public Employee loginEmailAndPassword(LoginModel loginModel) {
		return this.employeeRepository.findByEmailAndPassword(loginModel.getEmail(),EncryptionService.cryptWithMD5(loginModel.getPassword())).orElse(null);
	}
	
	public Employee loginEmailAndOauth(LoginModel loginModel) {
		return this.employeeRepository.findByEmailAndIsOauth(loginModel.getEmail(),loginModel.getIsOauth()).orElse(null);
	}
}
