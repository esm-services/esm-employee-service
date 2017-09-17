package com.esm.employee.service.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.esm.employee.service.domain.Employee;
import com.esm.employee.service.model.EmployeeModel;
import com.esm.employee.service.repository.EmployeeRepository;
import com.esm.employee.service.util.TestData;
import com.esm.employee.service.utils.DTOUtils;

public class EmployeeServiceImplTests {

	private EmployeeServiceImpl employeeServiceImpl;

	private EmployeeRepository mockEmployeeRepository;

	@Before
	public void setUp() {
		mockEmployeeRepository = mock(EmployeeRepository.class);
		employeeServiceImpl = new EmployeeServiceImpl(mockEmployeeRepository);
	}

	@Test
	public void shouldBeAbleToFindEmployeeByLastName() {
		List<Employee> employees = TestData.employees();
		when(mockEmployeeRepository.findByLastName(anyString())).thenReturn(employees);
		assertThat(employeeServiceImpl.findByLastName("RAI").getData().get(0).getLastName())
				.isEqualTo(employees.get(0).getLastName());
	}

	@Test
	public void shouldBeAbleToFindEmployeeByFirstName() {
		List<Employee> employees = TestData.employees();
		when(mockEmployeeRepository.findByFirstName(anyString())).thenReturn(employees);
		assertThat(employeeServiceImpl.findByFirstName("Durgesh").getData().get(0).getFirstName())
				.isEqualTo(employees.get(0).getFirstName());
	}

	@Test
	public void shouldBeAbleToFindEmployeeByDateOfBirth() {
		List<Employee> employees = TestData.employees();
		when(mockEmployeeRepository.findByDateOfBirth(anyObject())).thenReturn(employees);
		assertThat(employeeServiceImpl.findByDateOfBirth(LocalDate.of(2017, Month.SEPTEMBER, 1)).getData().get(0)
				.getDateOfBirth()).isEqualTo(employees.get(0).getDateOfBirth());
	}

	@Test
	public void shouldBeAbleToFindEmployeeByDateOfJoining() {
		List<Employee> employees = TestData.employees();
		when(mockEmployeeRepository.findByDateOfJoining(anyObject())).thenReturn(employees);
		assertThat(employeeServiceImpl.findByDateOfJoining(LocalDate.of(2017, Month.SEPTEMBER, 1)).getData().get(0)
				.getDateOfJoining()).isEqualTo(employees.get(0).getDateOfJoining());
	}

	@Test
	public void shouldBeAbleToFindEmployeeByEmployeeUID() {
		Employee employee = TestData.employee();
		when(mockEmployeeRepository.findByEmployeeUID(anyString())).thenReturn(employee);
		assertThat(employeeServiceImpl.findByEmployeeUID("durgesh.rai").getEmployeeUID())
				.isEqualTo(employee.getEmployeeUID());
	}

	@Test
	public void shouldBeAbleToCreateNewEmployee() {
		EmployeeModel employeeModel = TestData.employeeModel();
		Employee employee = DTOUtils.map(employeeModel, Employee.class);
		when(mockEmployeeRepository.save(employee)).thenReturn(employee);
		assertThat(employeeServiceImpl.newEmployee(employeeModel).getEmployeeUID())
				.isEqualTo(employeeModel.getEmployeeUID());
	}

	@Test
	public void shouldBeAbleToFindAllEmployees() {
		List<EmployeeModel> employeeModels = DTOUtils.mapList(TestData.employees(), EmployeeModel.class);
		when(mockEmployeeRepository.findAll()).thenReturn(TestData.employees());
		assertThat(employeeServiceImpl.findAllEmployees().getData().get(0).getEmployeeUID())
				.isEqualTo(employeeModels.get(0).getEmployeeUID());
	}
}
