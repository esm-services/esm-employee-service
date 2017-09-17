package com.esm.employee.service.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.esm.employee.service.domain.Employee;
import com.esm.employee.service.util.TestData;

@DataJpaTest
@RunWith(SpringRunner.class)
public class EmployeeRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void shouldBeAbleToFindEmployeeByLastName() {
		Employee employee = TestData.employee();
		entityManager.persist(employee);
		List<Employee> employees = employeeRepository.findByLastName("RAI");
		assertThat(employees.get(0).getLastName()).isEqualTo(employee.getLastName());
	}
}
