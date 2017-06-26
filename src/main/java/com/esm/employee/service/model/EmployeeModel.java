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

}
