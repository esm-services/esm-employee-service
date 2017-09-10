package com.esm.employee.service.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeModel implements Serializable{

	private static final long serialVersionUID = 1L;

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

	@Override
	public String toString() {
		return "EmployeeModel [employeeUID=" + employeeUID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", dateOfJoining=" + dateOfJoining + ", dateOfLeaving="
				+ dateOfLeaving + "]";
	}
}
