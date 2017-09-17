package com.esm.employee.service.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.esm.employee.service.domain.Employee;
import com.esm.employee.service.exception.ResourceNotFoundException;
import com.esm.employee.service.exception.UserNameAlreadyUsedException;
import com.esm.employee.service.model.EmployeeModel;
import com.esm.employee.service.model.Employees;
import com.esm.employee.service.repository.EmployeeRepository;
import com.esm.employee.service.utils.DTOUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private final EmployeeRepository employeeRepository;

	@Override
	public Employees findByLastName(String lastName) {
		List<Employee> employees = employeeRepository.findByLastName(lastName);
		if (employees == null || employees.isEmpty()) {
			throw new ResourceNotFoundException("No record found with lastName " + lastName);
		}
		List<EmployeeModel> employeeModels = DTOUtils.mapList(employees, EmployeeModel.class);
		return new Employees(employeeModels);
	}

	@Override
	public Employees findByFirstName(String firstName) {
		List<Employee> employees = employeeRepository.findByFirstName(firstName);
		if (employees == null || employees.isEmpty()) {
			throw new ResourceNotFoundException("No record found with firstName " + firstName);
		}
		List<EmployeeModel> employeeModels = DTOUtils.mapList(employees, EmployeeModel.class);
		return new Employees(employeeModels);
	}

	@Override
	public Employees findByDateOfBirth(LocalDate dateOfBirth) {
		List<Employee> employees = employeeRepository.findByDateOfBirth(dateOfBirth);
		if (employees == null || employees.isEmpty()) {
			throw new ResourceNotFoundException("No record found with Date Of Birth " + dateOfBirth);
		}
		List<EmployeeModel> employeeModels = DTOUtils.mapList(employees, EmployeeModel.class);
		return new Employees(employeeModels);
	}

	@Override
	public Employees findByDateOfJoining(LocalDate dateOfJoining) {
		List<Employee> employees = employeeRepository.findByDateOfJoining(dateOfJoining);
		if (employees == null || employees.isEmpty()) {
			throw new ResourceNotFoundException("No record found with Date of Joining " + dateOfJoining);
		}
		List<EmployeeModel> employeeModels = DTOUtils.mapList(employees, EmployeeModel.class);
		return new Employees(employeeModels);
	}

	@Override
	public EmployeeModel findByEmployeeUID(String employeeUID) {
		Employee employee = employeeRepository.findByEmployeeUID(employeeUID);
		if (employee == null) {
			throw new ResourceNotFoundException("No record found with uid " + employeeUID);
		}
		EmployeeModel employeeModel = DTOUtils.map(employee, EmployeeModel.class);
		return employeeModel;
	}

	@Override
	public EmployeeModel newEmployee(EmployeeModel employeeModel) {
		if (employeeRepository.findByEmployeeUID(employeeModel.getEmployeeUID()) != null) {
			throw new UserNameAlreadyUsedException("Employee already exist with the uid = " + employeeModel.getEmployeeUID());
		}
		Employee employee = DTOUtils.map(employeeModel, Employee.class);
		employee = employeeRepository.save(employee);
		return DTOUtils.map(employee, EmployeeModel.class);
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
	public Long updateEmployee(EmployeeModel employeeModel) {
		String employeeUID = employeeModel.getEmployeeUID();
		log.debug("update employee by employeeUID @" + employeeUID);
		Employee employee = employeeRepository.findByEmployeeUID(employeeUID);
		if (employee == null) {
			throw new ResourceNotFoundException("No record found with uid " + employeeUID);
		}
		DTOUtils.mapTo(employeeModel, employee);
		Employee updated = employeeRepository.save(employee);
		if (log.isDebugEnabled()) {
			log.debug("updated employee uith employeeID @" + updated.getEmployeeId());
		}
		return updated.getEmployeeId();
	}

	@Override
	public Employees findByDesignation(String designation) {
		List<Employee> employees = employeeRepository.findByDesignation(designation);
		if (employees == null || employees.isEmpty()) {
			throw new ResourceNotFoundException("No record found with Designation " + designation);
		}
		List<EmployeeModel> employeeModels = DTOUtils.mapList(employees, EmployeeModel.class);
		return new Employees(employeeModels);
	}

	@Override
	public void newEmployees(Employees employees) {
		List<String> rejectedData = new ArrayList<>();
		List<EmployeeModel> accepteddata = new ArrayList<>();

		for (EmployeeModel employee : employees.getData()) {
			if (employeeRepository.findByEmployeeUID(employee.getEmployeeUID()) == null) {
				accepteddata.add(employee);
			} else {
				rejectedData.add(employee.getEmployeeUID());
			}
		}

		List<Employee> data = DTOUtils.mapList(accepteddata, Employee.class);
		employeeRepository.save(data);

		if (!rejectedData.isEmpty()) {
			throw new UserNameAlreadyUsedException(rejectedData.toString());
		}
	}

	@Override
	public void updateEmployees(Employees employees) {
		List<String> rejected = new ArrayList<>();
		List<EmployeeModel> acceptedData = new ArrayList<>();
		List<Employee> employeeData = new ArrayList<>();

		for (EmployeeModel model : employees.getData()) {
			Employee employee = employeeRepository.findByEmployeeUID(model.getEmployeeUID());
			if (employee != null) {
				employeeData.add(employee);
				acceptedData.add(model);
			} else {
				rejected.add(model.getEmployeeUID());
			}
		}

		DTOUtils.mapToList(acceptedData, employeeData);
		employeeRepository.save(employeeData);

		if (!rejected.isEmpty()) {
			throw new ResourceNotFoundException(rejected.toString());
		}
	}
}
