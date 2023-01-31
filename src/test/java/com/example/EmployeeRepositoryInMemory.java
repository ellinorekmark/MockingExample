package com.example;

import java.util.List;

public class EmployeeRepositoryInMemory implements EmployeeRepository {

    Employee employee1 = new Employee("1", 32000);
    Employee employee2 = new Employee("2", 23500);
    Employee employee3 = new Employee("5", 28750);

    List<Employee> mockList = List.of(employee1, employee2, employee3);


    @Override
    public List<Employee> findAll() {
        return mockList;
    }

    @Override
    public Employee save(Employee e) {
        for (Employee employee : mockList) {
            if (e.getId().equals(employee.getId())) {
                employee = e;
                return employee;
            }
        }
        mockList.add(e);
        return e;
    }
}
