package com.example.testmockito.Controller;

import com.example.testmockito.Service.DepartmentsService;
import com.example.testmockito.Service.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
public class DepartmentsController {
    private final DepartmentsService departmentsService;

    public DepartmentsController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }





    @GetMapping("/department/{id}/salary/max")
    public double findMaxSalaryDepartment(@PathVariable("id") Integer department) {
        return departmentsService.findMaxSalaryDepartment(department);
    }

    @GetMapping("/department/{id}/salary/min")
    public double findMinSalaryDepartment(@PathVariable("id") Integer department) {
        return departmentsService.findMinSalaryDepartment(department);
    }
    @GetMapping("/department/{id}/employees" )
    public List<Employee> printEmployeeInTheDepartment(@PathVariable("id") Integer department){
        return departmentsService.printEmployeeInTheDepartment(department);
    }
    @GetMapping("/department/employees")
    public Map<Integer,List<Employee>> printDepartmentsEmployee(){
        return departmentsService.printDepartmentsEmployee();
    }
    @GetMapping("/department/{id}/salary/sum")
    public Integer printSumDepartment(@PathVariable("id")Integer department){
        return departmentsService.checkSumDepartment(department);
    }
}
