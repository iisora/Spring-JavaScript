package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Collection<Employee> findByNameLike(String name);

	@Query("SELECT e FROM Employee e WHERE e.name LIKE ?1")
	Collection<Employee> sarchByName(String name);

	@Query("SELECT e FROM Employee e WHERE e.name LIKE :name")
	Collection<Employee> searchByNameVariable(String name);

	@Query("SELECT e FROM Employee e WHERE e.name LIKE :#{employee.name}")
	Collection<Employee> searchByEmployeeObject(Employee employee);
}
