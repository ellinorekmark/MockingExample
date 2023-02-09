package com.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeManagerIntegrationTest {
    BankService bankService;
    EmployeeRepository employeeRepository;
    EmployeeManager employeeManager;

    @Test
    public void testPayEmployeesShouldChangePayStatus() {
        Employee dummy1 = new Employee("dummy1", 10);
        Employee dummy2 = new Employee("dummy2", 10);
        Employee dummy3 = new Employee("dummy3", 10);
        Employee dummy4 = new Employee("dummy4", 10);
        bankService = new BankServiceSpy();
        employeeRepository = new EmployeeRepositoryInMemory(List.of(dummy1, dummy2, dummy3, dummy4));
        employeeManager = new EmployeeManager(employeeRepository, bankService);
        employeeManager.payEmployees();
        for (Employee employee : employeeRepository.findAll()) {
            assertTrue(employee.isPaid());
        }
    }
}
