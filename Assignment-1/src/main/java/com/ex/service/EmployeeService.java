package com.ex.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.entity.Employee;
import com.ex.repo.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
class EmployeeService implements IEmployeeService{
	
    @Autowired
    private EmployeeRepository repository;
    
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Map<String, Double> calculateActualPercentage(int totalEmployees) {
        Map<String, Long> distribution = repository.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getRating, Collectors.counting()));
        return distribution.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (e.getValue() * 100.0) / totalEmployees));
    }

    
    
    public Map<String, Double> findDeviation(Map<String, Double> actual, Map<String, Double> standard) {
        Map<String, Double> deviation = new HashMap<>();

        for (String key : standard.keySet()) {
            double standardValue = standard.get(key);
            double actualValue = actual.getOrDefault(key, 0.0);
            double deviationValue = actualValue - standardValue;
           
            /*
            System.out.println("Category: " + key);
            System.out.println("Standard %: " + standardValue);
            System.out.println("Actual %: " + actualValue);
            System.out.println("Deviation %: " + deviationValue);
     */
            deviation.put(key, deviationValue);
        }
        return deviation;
    }
   
	@Override
	public void saveEmployee(Employee employee) {
		repository.save(employee);
		
	}
	
}
