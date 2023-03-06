package com.gridscape.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gridscape.employeemanagement.model.Department;
import com.gridscape.employeemanagement.model.Employee;
import com.gridscape.employeemanagement.repository.DepartmentRepository;
import com.gridscape.employeemanagement.repository.EmployeeRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Department getDepartmentByName(String departmentName) {
		return this.departmentRepository.findByName(departmentName).orElseThrow();
	}
	
	public Department saveDepartment(Department department) {
		return this.departmentRepository.save(department);
	}

	public List<Department> getAllDepartments() {
		return this.departmentRepository.findAll();
	}

	public void deleteById(Long departmentId) {
		this.departmentRepository.deleteById(departmentId);
	}

	public List<Employee> getEmployeeByDepartmentName(String departmentName) throws Exception{
		Department department=getDepartmentByName(departmentName);
		return this.employeeRepository.findByDepartmentId(department.getId());
	}
}
