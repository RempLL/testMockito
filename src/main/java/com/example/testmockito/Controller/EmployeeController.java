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
    public List<Employee> print() {
        return employeeService.getAll();
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
}
