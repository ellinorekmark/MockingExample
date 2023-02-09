package com.example;

import java.util.List;

public class EmployeeRepositoryInMemory implements EmployeeRepository {

    List<Employee> employees;

    public EmployeeRepositoryInMemory(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public Employee save(Employee e) {
        for (Employee employee : employees) {
            if (e.getId().equals(employee.getId())) {
                employee = e;
                return employee;
            }
        }
        employees.add(e);
        return e;
    }
}
