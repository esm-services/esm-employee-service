package com.esm.employee.service.util;

import java.time.LocalDate;
import java.time.Month;

import com.esm.employee.service.model.EmployeeModel;

public class TestData {

	public static EmployeeModel employee() {
		EmployeeModel employeeModel = new EmployeeModel("durgesh.rai", "Durgesh", "RAI", LocalDate.of(2017, Month.SEPTEMBER, 1),
				LocalDate.of(2017, Month.SEPTEMBER, 1), LocalDate.of(2017, Month.SEPTEMBER, 1));
		return employeeModel;
	}
}
