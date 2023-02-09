package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    public void testNewEmployeeIsNotPaid() {
        Employee employee = new Employee("DummyId", 33000);
        assertFalse(employee.isPaid());
    }


}