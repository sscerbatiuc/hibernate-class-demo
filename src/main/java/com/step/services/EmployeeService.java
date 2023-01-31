package com.step.services;

import com.step.model.Address;
import com.step.model.Department;
import com.step.model.Employee;
import com.step.model.dao.EmployeeDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDao();

    /**
     * This method generates
     *
     * @return
     */
    public List<Employee> getRandomEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Employee employee = new Employee("John " + i, "Smith " + i, LocalDate.of(i, 10, 10), 1500.0 * (i + 1));
            Address address = new Address("V.Alecsandri " + i, "Chisinau", "Moldova", "MD" + (2000 + i));
            employee.setAddress(address);
            employeeList.add(employee);
        }
        return employeeList;
    }

    public List<Employee> createRandomEmployees(List<Department> companyDepartments) {
        List<Employee> randomEmployees = getRandomEmployees();
        // 3. Link company departments with the employees
        for (int i = 0; i < randomEmployees.size(); i++) {
            randomEmployees.get(i).setDepartment(companyDepartments.get(i % companyDepartments.size()));
        }
        employeeDao.create(randomEmployees);
        return employeeDao.findAll();
    }

    public void create(Employee employee) {
        employeeDao.create(employee);
    }

    public void update(int i, Employee employee) {
        employeeDao.update(i, employee.getName(), employee.getSurname(), employee.getBirthdate());
    }

    public void printAll() {
        List<Employee> updatedEmployees = employeeDao.findAll();
        for (Employee emp : updatedEmployees) {
            System.out.println(emp);
        }
    }

    public void delete(int i) {
        employeeDao.delete(i);
    }
}
