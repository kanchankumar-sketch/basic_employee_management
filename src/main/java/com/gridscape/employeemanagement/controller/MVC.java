package com.gridscape.employeemanagement.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gridscape.employeemanagement.dto.LoginModel;
import com.gridscape.employeemanagement.model.Employee;
import com.gridscape.employeemanagement.model.UserLoginRecord;
import com.gridscape.employeemanagement.service.DepartmentService;
import com.gridscape.employeemanagement.service.EmployeeService;
import com.gridscape.employeemanagement.service.EncryptionService;
import com.gridscape.employeemanagement.service.UserLoginRecordService;

@RestController
public class MVC {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserLoginRecordService loginRecordService;

	@GetMapping("/home")
	public ModelAndView home(ModelAndView andView, HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		String email = (String) session.getAttribute("email");
		if (email != null) {
			Employee employee = this.employeeService.getEmployeeDetailsByEmail1(email);
			if (employee != null) {
				
				
				andView.setViewName("home");
				andView.addObject("username", employee.getName());
				andView.addObject("employee", employee);
				andView.addObject("employeeThis", employee);
				andView.addObject("employmentTypes", Arrays.asList("Permanent", "Contract", "Others"));
				andView.addObject("departments", this.departmentService.getAllDepartments());
			} else {
				session.invalidate();
				andView.addObject("message", "Session expired please login.");
				andView.setViewName("redirect:/login");
			}
		} else {
			andView.addObject("message", "Session expired please login.");
			andView.setViewName("redirect:/login");
		}
		return andView;
	}

	@GetMapping("/login")
	public ModelAndView login(ModelAndView andView, HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		String email = (String) session.getAttribute("email");
		if (email != null) {
			andView.setViewName("redirect:/home");
			return andView;
		}

		andView.setViewName("login");
		andView.addObject("login", new LoginModel());
		return andView;
	}

	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute LoginModel loginModel, ModelAndView andView,
			HttpServletRequest httpServletRequest) {

		HttpSession session = httpServletRequest.getSession();
		String email = (String) session.getAttribute("email");
		if (email != null) {
			if(!isValidEmail(email)) {
				andView.addObject("message", "Invalid credentials.");
				andView.setViewName("login");
				andView.addObject("login", loginModel);
				return andView;
			}
			
			
			andView.setViewName("redirect:/home");
			return andView;
		}

		Employee employee = null;
		if (loginModel.getIsOauth()) {
			if (!this.employeeService.getEmployeeDetailsByEmail(loginModel.getEmail()) && isValidEmail(loginModel.getEmail())) {
				Employee employeeOauth = new Employee();
				employeeOauth.setName(loginModel.getName());
				employeeOauth.setEmail(loginModel.getEmail());
				employeeOauth.setProfileUrl(loginModel.getProfileUrl());
				employeeOauth.setIsOauth(true);
				this.employeeService.saveEmployee(employeeOauth);
			}
			employee = this.employeeService.getEmployeeDetailsByEmail1(loginModel.getEmail());
		} else {
			employee = this.employeeService.loginEmailAndPassword(loginModel);
		}
		if (employee != null) {
			System.out.println(employee.getEmail()+"--"+(new Date()));
			this.loginRecordService.saveRecord(new UserLoginRecord(null,employee.getEmail(),new Date()));
			andView.setViewName("redirect:/home");
			session.setAttribute("email", employee.getEmail());
			return andView;
		} else {
			andView.addObject("message", "Invalid credentials.");
			andView.setViewName("login");
			andView.addObject("login", loginModel);
		}

		return andView;
	}

	@GetMapping("/registration")
	public ModelAndView registration(ModelAndView andView, HttpServletRequest httpServletRequest) {

		HttpSession session = httpServletRequest.getSession();
		String email = (String) session.getAttribute("email");
		if (email != null) {
			andView.setViewName("redirect:/home");
			return andView;
		} else {
			andView.setViewName("registration");
			andView.addObject("name", "");
			andView.addObject("employee", new Employee());
			andView.addObject("employmentTypes", Arrays.asList("Permanent", "Contract", "Others"));
		}
		return andView;
	}

	@PostMapping("/register")
	public ModelAndView registerEmployee(@ModelAttribute Employee employee, ModelAndView andView,
			HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		String email = (String) session.getAttribute("email");
		if (email != null) {
			andView.setViewName("redirect:/home");
			return andView;
		}

		if (!this.employeeService.getEmployeeDetailsByEmail(employee.getEmail())) {
			employee.setPassword(EncryptionService.cryptWithMD5(employee.getPassword()));
			this.employeeService.saveEmployee(employee);
			andView.setViewName("redirect:/login");
		} else {
			andView.setViewName("registration");
			andView.addObject("message", "Email already registered.");
			andView.addObject("employee", employee);
			andView.addObject("employmentTypes", Arrays.asList("Permanent", "Contract", "Others"));
		}
		return andView;
	}

	@PostMapping("/update")
	public ModelAndView updateEmployee(@ModelAttribute Employee employee, ModelAndView andView,
			HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		String email = (String) session.getAttribute("email");
		if (email != null) {
			Employee emp = this.employeeService.saveEmployee(employee);
			andView.setViewName("redirect:/home");
			return andView;
		} else {
			andView.setViewName("redirect:/login");
		}
		return andView;
	}

	@GetMapping("/logout")
	public ModelAndView logout(ModelAndView andView, HttpServletRequest httpServletRequest) {
		andView.setViewName("redirect:/login");
		HttpSession session = httpServletRequest.getSession();
		session.invalidate();
		return andView;
	}

	public boolean isValidEmail(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		if(email==null) {
			return false;
		}
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
