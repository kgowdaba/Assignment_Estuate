package com.ex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ex.entity.Employee;
import com.ex.repo.EmployeeRepository;
import com.ex.service.IEmployeeService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/employee")
class EmployeeController {
	
    @Autowired
    private IEmployeeService service;
    
    
    @GetMapping("/add-employee")
    public String showAddEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }
 
    @PostMapping("/save-employee")
    public String addEmployee(@ModelAttribute Employee employee,RedirectAttributes redirectAttributes) {
        service.saveEmployee(employee);
        redirectAttributes.addFlashAttribute("successMessage", "Employee added successfully!");
        return "redirect:/employee/add-employee";
    }
    

    @GetMapping("/all")
    public String showDashboard(Model model) {
        List<Employee> employees = service.getAllEmployees();
        int totalEmployees = employees.size();

        Map<String, Double> standard = Map.of("A", 10.0, "B", 20.0, "C", 40.0, "D", 20.0, "E", 10.0);
        Map<String, Double> actual = service.calculateActualPercentage(totalEmployees);
        Map<String, Double> deviation = service.findDeviation(actual, standard);
     
        model.addAttribute("employees", employees);
        model.addAttribute("actual", actual);
        model.addAttribute("deviation", deviation);
        model.addAttribute("categories", standard.keySet());
        model.addAttribute("standard", standard);
        model.addAttribute("actualPercentages", actual.values());

        return "Home";
    }
}

