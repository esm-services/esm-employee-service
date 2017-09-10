package com.esm.employee.service.business;

import java.time.LocalDate;

import com.esm.employee.service.model.EmployeeModel;
import com.esm.employee.service.model.Employees;

public interface EmployeeService {
	
	public EmployeeModel newEmployee(EmployeeModel employeeModel);
	
	public Long updateEmployee(EmployeeModel employeeModel);

	public Employees findByLastName(String lastName);

	public Employees findByFirstName(String firstName);

	public Employees findByDateOfBirth(LocalDate dateOfBirth);

	public Employees findByDateOfJoining(LocalDate dateOfJoining);
	
	public EmployeeModel findByEmployeeUID(String employeeUID);
	
	public Employees findAllEmployees();
	
	public Employees findByDesignation(String designation);
	
	public void newEmployees(Employees employees);
	
	public void updateEmployees(Employees employees);
}
