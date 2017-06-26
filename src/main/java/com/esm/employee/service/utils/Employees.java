package com.esm.employee.service.utils;

import java.util.List;

import com.esm.employee.service.model.EmployeeModel;

import lombok.Getter;

@Getter
public class Employees {

	private List<EmployeeModel> data;

	public Employees(List<EmployeeModel> data) {
		this.data = data;
	}
}
