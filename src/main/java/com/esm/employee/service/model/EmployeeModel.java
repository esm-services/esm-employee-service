package com.esm.employee.service.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeModel {

	private String employeeUID;

	private String firstName;

	private String lastName;

	private Date dateOfBirth;

	private Date dateOfJoining;

	private Date dateOfLeaving;
	
	public EmployeeModel(){}

	public EmployeeModel(String employeeUID, String firstName, String lastName, Date dateOfBirth, Date dateOfJoining,
			Date dateOfLeaving) {
		super();
		this.employeeUID = employeeUID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
		this.dateOfLeaving = dateOfLeaving;
	}
}
