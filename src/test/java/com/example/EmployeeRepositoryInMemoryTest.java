package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeRepositoryInMemoryTest {
    EmployeeRepositoryInMemory employeeRepository;
    static Employee dummy1 = new Employee("dummy1", 10);
    static Employee dummy2 = new Employee("dummy2", 10);
    static Employee dummy3 = new Employee("dummy3", 10);
    static Employee dummy4 = new Employee("dummy4", 10);

    @Test
    public void testSaveEmployeeShouldReplaceExistingEmployee() {
        employeeRepository = new EmployeeRepositoryInMemory(List.of(dummy1, dummy2));
        Employee employee = new Employee("dummy2", 12345);
        int employeeCount = employeeRepository.findAll().size();
        employeeRepository.save(employee);
        assertEquals(employeeCount, employeeRepository.findAll().size());
    }

    @ParameterizedTest
    @MethodSource("dummyEmployeeLists")
    public void testEmployeeInMemoryFindAllShouldReturnList(List<Employee> list, int count) {
        employeeRepository = new EmployeeRepositoryInMemory(list);
        List<Employee> employees = employeeRepository.findAll();
        assertEquals(count, employees.size());
    }

    private static Stream<Arguments> dummyEmployeeLists() {

        return Stream.of(
                Arguments.of(List.of(dummy1), 1),
                Arguments.of(List.of(dummy1, dummy2), 2),
                Arguments.of(List.of(dummy1, dummy2, dummy3), 3),
                Arguments.of(List.of(dummy1, dummy2, dummy3, dummy4), 4)
        );
    }

}
