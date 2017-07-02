package com.esm.employee.service.resource;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esm.employee.service.business.EmployeeService;
import com.esm.employee.service.model.EmployeeModel;
import com.esm.employee.service.utils.Employees;

@RestController
@RefreshScope
public class EmployeeResource {

	private final EmployeeService employeeService;

	public EmployeeResource(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping(value = "/employee")
	@PreAuthorize("#oauth2.hasScope('employee_write') and hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<EmployeeModel> newEmployee(@RequestBody EmployeeModel employeeModel) {
		EmployeeModel model = employeeService.newEmployee(employeeModel);
		return new ResponseEntity<>(model, HttpStatus.CREATED);
	}

	@PostMapping(value = "/employees")
	@PreAuthorize("#oauth2.hasScope('employee_write') and hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Void> newEmployees(@RequestBody Employees employeesModel) {
		employeeService.newEmployees(employeesModel);
		return ResponseEntity.accepted().build();
	}

	@PutMapping(value = "/employee")
	@PreAuthorize("#oauth2.hasScope('employee_write') and hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Long> updateEmployee(@RequestBody EmployeeModel employeeModel) {
		Long employeeID = employeeService.updateEmployee(employeeModel);
		return new ResponseEntity<>(employeeID, HttpStatus.OK);
	}

	@PutMapping(value = "/employees")
	@PreAuthorize("#oauth2.hasScope('employee_write') and hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Void> updateEmployees(@RequestBody Employees employeesModel) {
		employeeService.updateEmployees(employeesModel);
		return ResponseEntity.accepted().build();
	}

	@GetMapping(value = "/employees")
	@PreAuthorize("#oauth2.hasScope('employee_read') and hasAuthority('ROLE_USER','ROLE_ADMIN','ROLE_OPERATOR')")
	public ResponseEntity<Employees> allEmployees() {
		Employees employees = employeeService.findAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping(value = "/employees/{latName}")
	@PreAuthorize("#oauth2.hasScope('employee_read') and hasAuthority('ROLE_USER','ROLE_ADMIN','ROLE_OPERATOR')")
	public ResponseEntity<Employees> findEmployeesByLastName(@PathVariable String lastName) {
		Employees employees = employeeService.findByLastName(lastName);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping(value = "/employee")
	@PreAuthorize("#oauth2.hasScope('employee_read') and hasAuthority('ROLE_USER','ROLE_ADMIN','ROLE_OPERATOR')")
	public ResponseEntity<EmployeeModel> findEmployeeByUID(@RequestParam("employeeUID") String employeeUID) {
		EmployeeModel employee = employeeService.findByEmployeeUID(employeeUID);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
}
