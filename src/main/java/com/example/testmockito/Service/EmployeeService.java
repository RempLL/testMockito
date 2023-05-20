package com.example.testmockito.Service;

import com.example.testmockito.Exception.EmployeeAlreadyAddedException;
import com.example.testmockito.Exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private final ValidateService validateService;
    private Map<String, Employee> employee = new HashMap<>(Map.of(
            "1", new Employee("Альберт", "Миронов", 4, 20000),
            "2", new Employee("Виктор", "Меньшиков", 3, 40000),
            "3", new Employee("Андрей", "Абавикян", 2, 30000),
            "4", new Employee("Петр", "Первый", 1, 795000),
            "5", new Employee("Мария", "Воронова", 5, 40000),
            "6", new Employee("Федор", "Кислинкин", 4, 794000),
            "7", new Employee("Ренат", "Хабибулин", 2, 10000),
            "8", new Employee("Дмитрий", "Рогозов", 51, 775000),
            "9", new Employee("Роберт", "Иванов", 4, 60000),
            "10", new Employee("Павел", "Кучин", 1, 23000)));

    public EmployeeService(ValidateService validateService) {
        this.validateService = validateService;
    }

    public Employee addEmployee(String name, String surname, Integer department, Integer salary) {
        Employee newEmployee = new Employee(validateService.validateName(name), validateService.validateSurname(surname), department, salary);
        if (employee.containsValue(newEmployee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employee.put(String.valueOf(employee.size() + 1), newEmployee);
        return newEmployee;
    }

    public Employee removeEmployee(String name, String surname) {
        Employee newEmployee = new Employee(name, surname, 0, 0);

        Employee findEmployee = employee.values().stream()
                .filter(person -> person.getName().equals(newEmployee.getName()) && person.getSurname().equals(newEmployee.getSurname()))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);

        employee.values().remove(findEmployee);
        return findEmployee;
    }
    public Employee findEmployee(String name, String surname) {
        Employee newEmployee = new Employee(name, surname, 0, 0);

        return employee.values().stream()
                .filter(person -> person.getName().equals(newEmployee.getName()) && person.getSurname().equals(newEmployee.getSurname()))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAll(){
        return new ArrayList<>(employee.values());
    }
}
