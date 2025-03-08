package com.ex.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ex.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	List<Employee> findAll();
		
}
