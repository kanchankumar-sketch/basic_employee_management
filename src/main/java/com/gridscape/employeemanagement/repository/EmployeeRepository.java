package com.gridscape.employeemanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gridscape.employeemanagement.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

	List<Employee> findByDepartmentId(Long id);

//	List<Employee> findByGenderId(Long id);

	List<Employee> findByGender(String genderName);

	Optional<Employee> findByEmail(String email);

	Optional<Employee> findByEmailAndPassword(String email, String password);

	Optional<Employee> findByEmailAndIsOauth(String email,Boolean isOauth);

}
