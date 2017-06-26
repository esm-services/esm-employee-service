package com.esm.employee.service.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "T_ESM_EMPLOYEE")
@TableGenerator(name = "EMP_GEN", table = "EMP_ID_GEN")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMPLOYEE_ID")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EMP_GEN")
	private Long employeeId;

	@Column(name = "EMPLOYEE_UID")
	private String employeeUID;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;

	@Column(name = "DATE_OF_JOINING")
	private Date dateOfJoining;

	@Column(name = "DATE_OF_LEAVING")
	private Date dateOfLeaving;

	@Column(name = "DESIGNATION")
	private String designation;

}
