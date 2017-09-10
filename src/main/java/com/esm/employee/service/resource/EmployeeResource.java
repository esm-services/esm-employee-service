package com.esm.employee.service.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

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
import com.esm.employee.service.model.Employees;

import lombok.AllArgsConstructor;

@RefreshScope
@RestController
@AllArgsConstructor
public class EmployeeResource {

	private final EmployeeService employeeService;

	@PostMapping(value = "/employee", produces = APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("#oauth2.hasScope('write') and hasAuthority('ROLE_ACTUATOR')")
	public ResponseEntity<EmployeeModel> newEmployee(@RequestBody EmployeeModel employeeModel) {
		EmployeeModel model = employeeService.newEmployee(employeeModel);
		return new ResponseEntity<>(model, HttpStatus.CREATED);
	}

	@PostMapping(value = "/employees", consumes = APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("#oauth2.hasScope('write') and hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Void> newEmployees(@RequestBody Employees employeesModel) {
		employeeService.newEmployees(employeesModel);
		return ResponseEntity.accepted().build();
	}

	@PutMapping(value = "/employeeUpdate/{id}", consumes = APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("#oauth2.hasScope('write') and hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Long> updateEmployee(@PathVariable("id") int id, @RequestBody EmployeeModel employeeModel) {
		Long employeeID = employeeService.updateEmployee(employeeModel);
		return new ResponseEntity<>(employeeID, HttpStatus.OK);
	}

	@PutMapping(value = "/employeesUpdate/{id}", consumes = APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("#oauth2.hasScope('write') and hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Void> updateEmployees(@PathVariable("id") int id, @RequestBody Employees employeesModel) {
		employeeService.updateEmployees(employeesModel);
		return ResponseEntity.accepted().build();
	}

	@GetMapping(value = "/employees", produces = APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("#oauth2.hasScope('read') and hasAuthority('ROLE_ACTUATOR')")
	public ResponseEntity<Employees> allEmployees() {
		Employees employees = employeeService.findAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping(value = "/employees/{latName}", produces = APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("#oauth2.hasScope('read') and hasAuthority('ROLE_USER')")
	public ResponseEntity<Employees> findEmployeesByLastName(@PathVariable String lastName) {
		Employees employees = employeeService.findByLastName(lastName);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping(value = "/employee", produces = APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("#oauth2.hasScope('read') and hasAuthority('ROLE_USER')")
	public ResponseEntity<EmployeeModel> findEmployeeByUID(@RequestParam("employeeUID") String employeeUID) {
		EmployeeModel employee = employeeService.findByEmployeeUID(employeeUID);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
}
