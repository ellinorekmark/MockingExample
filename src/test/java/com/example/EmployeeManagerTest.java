package com.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeManagerTest {
    EmployeeRepositoryInMemory employeeRepository;

    BankService bankService = Mockito.mock(BankService.class);
    EmployeeManager employeeManager;

    @BeforeEach
    public void initializeEmployeeManager(){
        employeeRepository = new EmployeeRepositoryInMemory();
        employeeManager = new EmployeeManager(employeeRepository,bankService);
    }


    @Test
    public void testPayEmployeesShouldUseBankService(){
    //spy?
        employeeManager.payEmployees();
        verify(bankService, Mockito.atLeast(1)).pay("1", 32000);

    }

    @Test
    public void testPayEmployeesShouldPayAll(){
        int payments = employeeManager.payEmployees();
        assertEquals(employeeRepository.findAll().size(), payments);
    }

    @Test
    public void testPayEmployeesShouldChangePayStatus(){
        employeeManager.payEmployees();
        for (Employee employee : employeeRepository.findAll()) {
            assertTrue(employee.isPaid());
        }
    }
    @Test
    public void testEmployeeRepositoryFindAllShouldReturnListWithEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        assert(employees.size()!=0);
    }

    @Test
    public void testSaveEmployeeShouldReplaceExistingEmployee(){
        Employee employee = new Employee("2", 12345);
        int employeeCount = employeeRepository.findAll().size();
        employeeRepository.save(employee);
        assertEquals(employeeCount, employeeRepository.findAll().size());

    }

}