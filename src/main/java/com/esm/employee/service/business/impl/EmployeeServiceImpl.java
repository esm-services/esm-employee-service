package com.esm.employee.service.business.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esm.employee.service.business.EmployeeService;
import com.esm.employee.service.domain.Employee;
import com.esm.employee.service.exception.ResourceNotFoundException;
import com.esm.employee.service.exception.UsernameAlreadyUsedException;
import com.esm.employee.service.model.EmployeeModel;
import com.esm.employee.service.repository.EmployeeRepository;
import com.esm.employee.service.utils.DTOUtils;
import com.esm.employee.service.utils.Employees;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeServiceImpl) {
		this.employeeRepository = employeeServiceImpl;
	}

	@Override
	public Employees findByLastName(String lastName) {
		List<Employee> employees = employeeRepository.findByLastName(lastName);
		if (employees == null || employees.isEmpty()) {
			throw new ResourceNotFoundException(lastName);
		}
		List<EmployeeModel> employeeModels = DTOUtils.mapList(employees, EmployeeModel.class);
		return new Employees(employeeModels);
	}

	@Override
	public Employees findByFirstName(String firstName) {
		List<Employee> employees = employeeRepository.findByFirstName(firstName);
		if (employees == null || employees.isEmpty()) {
			throw new ResourceNotFoundException(firstName);
		}
		List<EmployeeModel> employeeModels = DTOUtils.mapList(employees, EmployeeModel.class);
		return new Employees(employeeModels);
	}

	@Override
	public Employees findByDateOfBirth(Date dateOfBirth) {
		List<Employee> employees = employeeRepository.findByDateOfBirth(dateOfBirth);
		if (employees == null || employees.isEmpty()) {
			throw new ResourceNotFoundException(dateOfBirth);
		}
		List<EmployeeModel> employeeModels = DTOUtils.mapList(employees, EmployeeModel.class);
		return new Employees(employeeModels);
	}

	@Override
	public Employees findByDateOfJoining(Date dateOfJoining) {
		List<Employee> employees = employeeRepository.findByDateOfJoining(dateOfJoining);
		if (employees == null || employees.isEmpty()) {
			throw new ResourceNotFoundException(dateOfJoining);
		}
		List<EmployeeModel> employeeModels = DTOUtils.mapList(employees, EmployeeModel.class);
		return new Employees(employeeModels);
	}

	@Override
	public EmployeeModel findByEmployeeUID(String employeeUID) {
		Employee employee = employeeRepository.findByEmployeeUID(employeeUID);
		if (employee == null) {
			throw new ResourceNotFoundException(employeeUID);
		}
		EmployeeModel employeeModel = DTOUtils.map(employee, EmployeeModel.class);
		return employeeModel;
	}

	@Override
	public Long newEmployee(EmployeeModel employeeModel) {
		if (employeeRepository.findByEmployeeUID(employeeModel.getEmployeeUID()) != null) {
			throw new UsernameAlreadyUsedException(employeeModel.getEmployeeUID());
		}
		Employee employee = DTOUtils.map(employeeModel, Employee.class);
		employee = employeeRepository.save(employee);
		return employee.getEmployeeId();
	}

	@Override
	public Employees findAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		if (employees == null || employees.isEmpty()) {
			throw new ResourceNotFoundException("There are no employees joined yet");
		}
		List<EmployeeModel> employeeModels = DTOUtils.mapList(employees, EmployeeModel.class);
		return new Employees(employeeModels);
	}

	@Override
	public Long updateEmployeeDetails(Long employeeId, EmployeeModel employeeModel) {
		log.debug("update employee by employeeId @" + employeeId);
		Employee employee = employeeRepository.findOne(employeeId);
		if (employee == null) {
			throw new ResourceNotFoundException(employeeId);
		}
		DTOUtils.mapTo(employeeModel, employee);
		Employee updated = employeeRepository.save(employee);
		if (log.isDebugEnabled()) {
			log.debug("updated employee @" + updated);
		}
		return updated.getEmployeeId();
	}

	@Override
	public Employees findByDesignation(String designation) {
		List<Employee> employees = employeeRepository.findByDesignation(designation);
		if (employees == null || employees.isEmpty()) {
			throw new ResourceNotFoundException(designation);
		}
		List<EmployeeModel> employeeModels = DTOUtils.mapList(employees, EmployeeModel.class);
		return new Employees(employeeModels);
	}

}
