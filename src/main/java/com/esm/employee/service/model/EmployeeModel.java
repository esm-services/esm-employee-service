package com.esm.employee.service.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeModel {

	private String employeeUID;

	private String firstName;

	private String lastName;

	private LocalDate dateOfBirth;

	private LocalDate dateOfJoining;

	private LocalDate dateOfLeaving;

	public EmployeeModel() {
	}

	public EmployeeModel(String employeeUID, String firstName, String lastName, LocalDate dateOfBirth,
			LocalDate dateOfJoining, LocalDate dateOfLeaving) {
		this.employeeUID = employeeUID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
		this.dateOfLeaving = dateOfLeaving;
	}
}
