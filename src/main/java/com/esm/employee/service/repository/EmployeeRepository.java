/**
 * 
 */
package com.esm.employee.service.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esm.employee.service.domain.Employee;

/**
 * @author Durgesh RAI
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public List<Employee> findByLastName(String lastName);

	public List<Employee> findByFirstName(String firstName);

	public List<Employee> findByDateOfBirth(LocalDate dateOfBirth);

	public List<Employee> findByDateOfJoining(LocalDate dateOfJoining);

	public Employee findByEmployeeUID(String employeeUID);

	public List<Employee> findByDesignation(String designation);
}
