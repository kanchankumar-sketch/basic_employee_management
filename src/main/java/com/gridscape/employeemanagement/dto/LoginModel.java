package com.gridscape.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginModel {
	private String name;
	private String profileUrl;
	private String email;
	private String password;
	private Boolean isOauth=false;
}
