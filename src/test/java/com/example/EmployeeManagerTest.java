package com.example;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeManagerTest {
    static Employee dummy1 = new Employee("dummy1", 10);
    static Employee dummy2 = new Employee("dummy2", 10);
    static Employee dummy3 = new Employee("dummy3", 10);
    static Employee dummy4 = new Employee("dummy4", 10);
    EmployeeRepository employeeRepository;
    BankService bankService;
    EmployeeManager employeeManager;

    @Test
    public void testPayEmployeesShouldUseBankService() {
        bankService = mock(BankService.class);
        employeeRepository = new EmployeeRepositoryInMemory(List.of(dummy3, dummy1, dummy2, dummy4));
        employeeManager = new EmployeeManager(employeeRepository, bankService);
        employeeManager.payEmployees();
        verify(bankService, atLeast(1)).pay("dummy1", 10);
    }

    @ParameterizedTest
    @MethodSource("mockEmployeeLists")
    public void testPayEmployeesShouldUseBankServiceEqualTimesToEmployees(List<Employee> list, int expectedCount) {
        BankServiceSpy bankServiceSpy = new BankServiceSpy();
        employeeRepository = mock(EmployeeRepository.class);
        employeeManager = new EmployeeManager(employeeRepository, bankServiceSpy);
        when(employeeRepository.findAll()).thenReturn(list);
        employeeManager.payEmployees();
        int bankServiceCount = bankServiceSpy.getCount();
        assertEquals(expectedCount, bankServiceCount);
    }

    private static Stream<Arguments> mockEmployeeLists() {
        return Stream.of(
                Arguments.of(List.of(), 0),
                Arguments.of(List.of(dummy1, dummy2), 2),
                Arguments.of(List.of(dummy1, dummy2, dummy3, dummy4), 4)
        );
    }

    @Test
    public void testEmptyListOfEmployeesShouldPay0() {
        bankService = mock(BankService.class);
        employeeRepository = mock(EmployeeRepository.class);
        employeeManager = new EmployeeManager(employeeRepository, bankService);
        when(employeeRepository.findAll()).thenReturn(List.of());
        int payments = employeeManager.payEmployees();
        assertEquals(0, payments);
    }

    @Test
    public void testPayEmployeesShouldPayAllInList() {
        bankService = new BankServiceSpy();
        employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findAll()).thenReturn(List.of(dummy1, dummy2, dummy3, dummy4));
        employeeManager = new EmployeeManager(employeeRepository, bankService);
        int payments = employeeManager.payEmployees();
        assertEquals(4, payments);
    }
}