package com.esm.employee.service.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.esm.employee.service.utils.LocalDateTimeDeserializer;
import com.esm.employee.service.utils.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String employeeUID;

	private String firstName;

	private String lastName;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDate dateOfBirth;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDate dateOfJoining;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDate dateOfLeaving;

	private String designation;

	@Override
	public String toString() {
		return "EmployeeModel [employeeUID=" + employeeUID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", dateOfJoining=" + dateOfJoining + ", dateOfLeaving="
				+ dateOfLeaving + ", designation=" + designation + "]";
	}
}
