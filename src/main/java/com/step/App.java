package com.step;

import com.step.hibernate.HibernateUtil;
import com.step.model.Employee;
import com.step.model.EmployeeDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        // 1. Create random objects.
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            employeeList.add(new Employee("Name " + i, "Surname " + i, LocalDate.of(i, 10,10)));
        }
        // 2. Save random objects
        dao.create(employeeList);

        // 3. Create single object
        dao.create("John", "Smith", LocalDate.of(999, 9, 9));

        // 4. Update single object
        dao.update(1, "UPDATED NAME", "UPDATED SURNAME", LocalDate.of(1999, 9, 9));

        // 5. Display the updated values
        List<Employee> updatedEmployees = dao.findAll();
        for (Employee emp: updatedEmployees) {
            System.out.println(emp);
        }

        // 6. Delete object by ID
        dao.delete(5);
        HibernateUtil.shutdown();
    }
}
