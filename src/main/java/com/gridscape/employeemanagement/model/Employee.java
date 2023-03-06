package com.gridscape.employeemanagement.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "employee")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dob;
	private String email;
	private String phoneNumber;
	private String password;
	private String address;
	private String employementType;
	private String profileUrl;

	private Long departmentId;
	private String gender;
	private Boolean isOauth=false;
}
