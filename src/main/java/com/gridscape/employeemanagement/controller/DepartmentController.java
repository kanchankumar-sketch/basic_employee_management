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

import com.gridscape.employeemanagement.model.Department;
import com.gridscape.employeemanagement.model.Employee;
import com.gridscape.employeemanagement.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping
	public Department saveDepartment(@RequestBody Department department) {
		return this.departmentService.saveDepartment(department);
	}
	
	@GetMapping
	public List<Department> getAllDepartment(){
		return this.departmentService.getAllDepartments();
	}
	
	@PutMapping
	public Department updateDepartment(@RequestBody Department department) {
		return this.departmentService.saveDepartment(department);
	}
	
	@DeleteMapping("/{departmentId}")
	public String deleteDepartment(@PathVariable("departmentId") Long departmentId) {
		this.departmentService.deleteById(departmentId);
		return "deleted successfully";
	}
	
	@GetMapping("/{departmentName}/employees")
	public List<Employee> getAllEmployeeByDepartmentName(@PathVariable("departmentName") String departmentName){
		try {
			return this.departmentService.getEmployeeByDepartmentName(departmentName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
