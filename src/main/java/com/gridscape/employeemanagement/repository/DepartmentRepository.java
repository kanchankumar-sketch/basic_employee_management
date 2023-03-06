package com.gridscape.employeemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gridscape.employeemanagement.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long>{

	Optional<Department> findByName(String departmentName);

}
