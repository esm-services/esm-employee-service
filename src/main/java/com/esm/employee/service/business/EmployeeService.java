package com.esm.employee.service.business;

import java.util.Date;

import com.esm.employee.service.model.EmployeeModel;
import com.esm.employee.service.utils.Employees;

public interface EmployeeService {
	
	public Long newEmployee(EmployeeModel employeeModel);
	
	public Long updateEmployeeDetails(Long employeeId, EmployeeModel employeeModel);

	public Employees findByLastName(String lastName);

	public Employees findByFirstName(String firstName);

	public Employees findByDateOfBirth(Date dateOfBirth);

	public Employees findByDateOfJoining(Date dateOfJoining);
	
	public EmployeeModel findByEmployeeUID(String employeeUID);
	
	public Employees findAllEmployees();
	
	public Employees findByDesignation(String designation);
}
