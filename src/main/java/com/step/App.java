package com.step;

import com.step.hibernate.HibernateUtil;
import com.step.model.Department;
import com.step.model.Employee;
import com.step.services.DepartmentService;
import com.step.services.EmployeeService;

import java.time.LocalDate;
import java.util.List;

public class App {
    private final static EmployeeService employeeService = new EmployeeService();
    private final static DepartmentService departmentService = new DepartmentService();


    public static void main(String[] args) {

        // 1. Create random departments
        List<Department> companyDepartments = departmentService.createRandomCompanyDepartments();
        departmentService.printAll();

        // 2. Create random objects.
        employeeService.createRandomEmployees(companyDepartments);
        employeeService.printAll();

        // 5. Create single object
        employeeService.create(new Employee("John", "Smith", LocalDate.of(999, 9, 9), 200.0));

        // 6. Update single object
        employeeService.update(1, new Employee("UPDATED NAME", "UPDATED SURNAME", LocalDate.of(1999, 9, 9), 4555.5));

        // 7. Display the updated values
        employeeService.printAll();

        // 8. Delete object by ID
        // Note: this call will also delete the address from the address table
        employeeService.delete(5);

        // 9. Close hibernate
        HibernateUtil.shutdown();
    }





}
