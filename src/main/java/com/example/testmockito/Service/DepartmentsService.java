package com.example.testmockito.Service;

import com.example.testmockito.Exception.DepartmentNotFoundException;
import com.example.testmockito.Exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentsService {

    private final EmployeeService employeeService;

    public DepartmentsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public double findMaxSalaryDepartment(Integer department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .max(Comparator.naturalOrder())
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public double findMinSalaryDepartment(Integer department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .min(Comparator.naturalOrder())
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public List<Employee> printEmployeeInTheDepartment(Integer department) {

        List<Employee> result = employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .toList();

        if (result.isEmpty()) {
            throw new EmployeeNotFoundException();
        }
        return result;
    }

    public Map<Integer,List<Employee>> printDepartmentsEmployee() {
        System.out.println(employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment)));
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public Integer checkSumDepartment(Integer department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .mapToInt(Employee::getSalary)
                .sum();
    }
}
