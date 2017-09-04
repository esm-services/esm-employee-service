package com.esm.employee.service.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_EMPLOYEE")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_EMPLOYEE")
	@SequenceGenerator(sequenceName = "S_EMPLOYEE", name = "S_EMPLOYEE")
	@Column(name = "EMPLOYEE_ID", nullable = false, updatable = false)
	private Long employeeId;

	@Column(name = "EMPLOYEE_UID", nullable = false, updatable = false)
	private String employeeUID;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "DATE_OF_BIRTH", nullable = false)
	private LocalDate dateOfBirth;

	@Column(name = "DATE_OF_JOINING", nullable = false)
	private LocalDate dateOfJoining;

	@Column(name = "DATE_OF_LEAVING", nullable = true)
	private LocalDate dateOfLeaving;

	@Column(name = "DESIGNATION", nullable = false)
	private String designation;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((employeeUID == null) ? 0 : employeeUID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (employeeUID == null) {
			if (other.employeeUID != null)
				return false;
		} else if (!employeeUID.equals(other.employeeUID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeUID=" + employeeUID + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", dateOfJoining=" + dateOfJoining
				+ ", dateOfLeaving=" + dateOfLeaving + ", designation=" + designation + "]";
	}
}
