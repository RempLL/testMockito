package com.example.testmockito.Controller;


import com.example.testmockito.Service.Employee;
import com.example.testmockito.Service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public String print() {
        return employeeService.print();
    }

    @GetMapping("/employee/add")
    public String add(@RequestParam("firstName") String name, @RequestParam("lastName") String surname, @RequestParam("department") Integer department, @RequestParam("salary") Integer salary) {
        return employeeService.addEmployee(name, surname, department, salary) + " добавлен в сотрудники";
    }

    @GetMapping("/employee/remove")
    public String remove(@RequestParam("firstName") String name, @RequestParam("lastName") String surname) {
        return employeeService.removeEmployee(name, surname) + " удален";
    }

    @GetMapping("/employee/find")
    public String find(@RequestParam("firstName") String name, @RequestParam("lastName") String surname) {
        return employeeService.findEmployee(name, surname) + " найден";
    }

    @GetMapping("/departments/max-salary")
    public String findMaxSalaryDepartment(@RequestParam("departmentId") Integer department) {
        return employeeService.findMaxSalaryDepartment(department) + " имеет наибольшую ЗП";
    }

    @GetMapping("/departments/min-salary")
    public String findMinSalaryDepartment(@RequestParam("departmentId") Integer department) {
        return employeeService.findMinSalaryDepartment(department) + " имеет наименьшую ЗП";
    }

    @GetMapping("/departments/all")
    public List<Employee> printDepartmentsEmployee(@RequestParam(value = "departmentId", required = false) Integer department) {
        if (department == null) {
            return employeeService.printDepartmentsEmployee();
        }
        return employeeService.printEmployeeInTheDepartment(department);
    }
}
