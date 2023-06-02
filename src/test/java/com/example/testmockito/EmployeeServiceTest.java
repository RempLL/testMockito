package com.example.testmockito;


import com.example.testmockito.Exception.EmployeeAlreadyAddedException;
import com.example.testmockito.Exception.EmployeeNotFoundException;
import com.example.testmockito.Exception.IncorrectNameException;
import com.example.testmockito.Exception.IncorrectSurnameException;
import com.example.testmockito.Service.Employee;
import com.example.testmockito.Service.EmployeeService;
import com.example.testmockito.Service.ValidateService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService(new ValidateService());

    public static Stream<Arguments> addIncorrectNameTest() {
        return Stream.of(
                Arguments.of("Петя2"),
                Arguments.of("Жо-ра")

        );
    }

    public static Stream<Arguments> addIncorrectSurnameTest() {
        return Stream.of(
                Arguments.of("Викт0рович"),
                Arguments.of("Меш@фф")

        );
    }

    @BeforeEach
    public void beforeEach() {
        employeeService.getAll()
                .forEach(employee -> employeeService.removeEmployee(employee.getName(), employee.getSurname()));
        employeeService.addEmployee("Михаил", "Вагонкин", 2, 20);
        employeeService.addEmployee("Артем", "Махоуни", 3, 40);
        employeeService.addEmployee("Митя", "Виго", 5, 30);
    }

    @AfterEach
    public void afterEach() {
        employeeService.getAll()
                .forEach(employee -> employeeService.removeEmployee(employee.getName(), employee.getSurname()));
    }


    @Test
    public void addEmployeeTest() {
        int count = employeeService.getAll().size();
        Employee expected = new Employee("Петр", "Григо", 2, 333);


        Assertions.assertThat(employeeService.addEmployee("Петр", "Григо", 2, 333))
                .isEqualTo(expected)
                .isIn(employeeService.getAll());
        Assertions.assertThat(employeeService.getAll()).hasSize(count + 1);
    }

    @ParameterizedTest
    @MethodSource("addIncorrectNameTest")
    public void addIncorrectNameTest(String incorrectName) {
        Assertions.assertThatExceptionOfType(IncorrectNameException.class)
                .isThrownBy(() -> employeeService.addEmployee(incorrectName, "Григо", 2, 333));
    }


    @ParameterizedTest
    @MethodSource("addIncorrectSurnameTest")
    public void addIncorrectSurnameTest(String incorrectSurname) {
        Assertions.assertThatExceptionOfType(IncorrectSurnameException.class)
                .isThrownBy(() -> employeeService.addEmployee("Маня", incorrectSurname, 2, 333));
    }

    @Test
    public void addAlreadyAddedExceptionTest() {
        Assertions.assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.addEmployee("Михаил", "Вагонкин", 2, 20));
    }

    @Test
    public void removeEmployeeTest() {
        int count = employeeService.getAll().size();
        Employee expected = new Employee("Михаил", "Вагонкин", 2, 20);
        Assertions.assertThat(employeeService.removeEmployee("Михаил", "Вагонкин"))
                .isEqualTo(expected)
                .isNotIn(employeeService.getAll());
        Assertions.assertThat(employeeService.getAll()).hasSize(count - 1);
    }

    @Test
    public void removeEmployeeNotFoundExceptionTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.removeEmployee("Ваня", "Пони"));
    }

    @Test
    public void findEmployeeTest() {
        Employee expected = new Employee("Михаил", "Вагонкин", 2, 20);

        Assertions.assertThat(employeeService.findEmployee("Михаил", "Вагонкин"))
                .isEqualTo(expected)
                .isIn(employeeService.getAll());

    }

    @Test
    public void findEmployeeNotFoundExceptionTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("Ваня", "Пони"));
    }

    @Test
    public void getAllEmployeeTest() {
        Assertions.assertThat(employeeService.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Employee("Михаил", "Вагонкин", 2, 20),
                        new Employee("Артем", "Махоуни", 3, 40),
                        new Employee("Митя", "Виго", 5, 30)

                );
    }

}
