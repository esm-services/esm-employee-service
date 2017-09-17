package com.esm.employee.service.util;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import com.esm.employee.service.domain.Employee;
import com.esm.employee.service.model.EmployeeModel;

public class TestData {

	public static EmployeeModel employeeModel() {
		EmployeeModel employeeModel = new EmployeeModel("durgesh.rai", "Durgesh", "RAI",
				LocalDate.of(2017, Month.SEPTEMBER, 1), LocalDate.of(2017, Month.SEPTEMBER, 1),
				LocalDate.of(2017, Month.SEPTEMBER, 1), "Engineer");
		return employeeModel;
	}

	public static List<EmployeeModel> employeeModels() {
		return Arrays.asList(employeeModel());
	}

	public static List<Employee> employees() {
		return Arrays.asList(employee());
	}

	public static Employee employee() {
		Employee employee = new Employee("durgesh.rai", "Durgesh", "RAI", LocalDate.of(2017, Month.SEPTEMBER, 1),
				LocalDate.of(2017, Month.SEPTEMBER, 1), LocalDate.of(2017, Month.SEPTEMBER, 1), "Engineer");
		return employee;
	}
}
