package com.example.testmockito;

import com.example.testmockito.Exception.DepartmentNotFoundException;
import com.example.testmockito.Exception.EmployeeNotFoundException;
import com.example.testmockito.Service.DepartmentsService;
import com.example.testmockito.Service.Employee;
import com.example.testmockito.Service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class DepartmentsServiceTest {

    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentsService departmentsService;


    public static Stream<Arguments> findMaxSalaryDepartmentParams() {
        return Stream.of(
                Arguments.of(
                        1, 40000),
                Arguments.of(
                        2, 20000),
                Arguments.of(
                        6, 20000)

        );
    }

    public static Stream<Arguments> findMinSalaryDepartmentParams() {
        return Stream.of(
                Arguments.of(
                        1, 30000),
                Arguments.of(
                        2, 10000),
                Arguments.of(
                        6, 20000)
        );
    }

    public static Stream<Arguments> printEmployeeInTheDepartmentParams() {
        return Stream.of(
                Arguments.of(1,
                        List.of(new Employee("Ваня", "Кокос", 1, 30000),
                                new Employee("Вагон", "Синий", 1, 40000))
                ),
                Arguments.of(2,
                        List.of(new Employee("Митяй", "Бочкин", 2, 10000),
                                new Employee("Маша", "Боровчук", 2, 20000))
                ),
                Arguments.of(
                        6,
                        List.of(new Employee("Проня", "Вовков", 6, 20000)))
        );
    }

    public static Stream<Arguments> checkSumDepartmentTestParams() {
        return Stream.of(
                Arguments.of(
                        1, 70000),
                Arguments.of(
                        2, 30000),
                Arguments.of(
                        6, 20000)
        );
    }


    @BeforeEach
    public void beforeEach() {
        Mockito.when(employeeService.getAll()).thenReturn(
                List.of(
                        new Employee("Маша", "Боровчук", 2, 20000),
                        new Employee("Ваня", "Кокос", 1, 30000),
                        new Employee("Митяй", "Бочкин", 2, 10000),
                        new Employee("Вагон", "Синий", 1, 40000),
                        new Employee("Проня", "Вовков", 6, 20000)
                ));
    }

    @ParameterizedTest
    @MethodSource("findMaxSalaryDepartmentParams")
    public void findMaxSalaryDepartmentTest(int department, double expected) {
        Assertions.assertThat(departmentsService.findMaxSalaryDepartment(department))
                .isEqualTo(expected);
    }

    @Test
    public void findMaxSalaryDepartmentNegativeTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentsService.findMaxSalaryDepartment(3));
    }

    @ParameterizedTest
    @MethodSource("findMinSalaryDepartmentParams")
    public void findMinSalaryDepartmentTest(int department, double expected) {
        Assertions.assertThat(departmentsService.findMinSalaryDepartment(department))
                .isEqualTo(expected);
    }

    @Test
    public void findMinSalaryDepartmentNegativeTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentsService.findMinSalaryDepartment(3));
    }

    @ParameterizedTest
    @MethodSource("printEmployeeInTheDepartmentParams")
    public void printEmployeeInTheDepartmentTest(int department, List<Employee> expected) {
        Assertions.assertThat(departmentsService.printEmployeeInTheDepartment(department))
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void printEmployeeInTheDepartmentNegativeTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentsService.printEmployeeInTheDepartment(3));
    }

    @Test
    public void printDepartmentsEmployeeTest() {

        Map<Integer, List<Employee>> expected = Map.of(
                1,
                List.of(
                        new Employee("Ваня", "Кокос", 1, 30000),
                        new Employee("Вагон", "Синий", 1, 40000)),
                2,
                List.of(
                        new Employee("Маша", "Боровчук", 2, 20000),
                        new Employee("Митяй", "Бочкин", 2, 10000)),

                6,
                List.of(
                        new Employee("Проня", "Вовков", 6, 20000))
        );
        Assertions.assertThat(departmentsService.printDepartmentsEmployee())
                .containsExactlyInAnyOrderEntriesOf(expected);
    }

    @ParameterizedTest
    @MethodSource("checkSumDepartmentTestParams")
    public void checkSumDepartmentTest(int department, int expected) {
        Assertions.assertThat(departmentsService.checkSumDepartment(department))
                .isEqualTo(expected);

    }
}
