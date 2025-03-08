package com.ex.service;

import java.util.List;
import java.util.Map;

import com.ex.entity.Employee;

public interface IEmployeeService {
	public List<Employee> getAllEmployees();
	
	public Map<String, Double> calculateActualPercentage(int totalEmployees);
	
	public Map<String, Double> findDeviation(Map<String, Double> actual, Map<String, Double> standard);
	
	public void saveEmployee(Employee employee);

}
