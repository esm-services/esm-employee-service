package com.esm.employee.service.resource;

import javax.websocket.server.PathParam;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<Long> newEmployee(@RequestBody EmployeeModel employeeModel) {
		Long employeeID = employeeService.newEmployee(employeeModel);
		return new ResponseEntity<>(employeeID, HttpStatus.OK);
	}

	@PutMapping(value = "/employee/{employeeID}")
	public ResponseEntity<Long> updateEmployeeDetails(@PathVariable("employeeID") Long employeeID,
			@RequestBody EmployeeModel employeeModel) {
		employeeID = employeeService.updateEmployeeDetails(employeeID, employeeModel);
		return new ResponseEntity<>(employeeID, HttpStatus.OK);
	}

	@GetMapping(value = "/employees")
	public ResponseEntity<Employees> allEmployees() {
		Employees employees = employeeService.findAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping(value = "/employee/{latName}")
	public ResponseEntity<Employees> employeeByLastName(@PathVariable("latName") String lastName) {
		Employees employees = employeeService.findByLastName(lastName);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@GetMapping(value = "/employee")
	public ResponseEntity<EmployeeModel> employeeByUID(@PathParam("employeeUID") String employeeUID) {
		EmployeeModel employee = employeeService.findByEmployeeUID(employeeUID);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
}
