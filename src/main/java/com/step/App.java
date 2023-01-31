package com.step;

import com.step.hibernate.HibernateUtil;
import com.step.model.Address;
import com.step.model.Department;
import com.step.model.Employee;
import com.step.model.dao.DepartmentDao;
import com.step.model.dao.EmployeeDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static EmployeeDao employeeDao = new EmployeeDao();
    private static DepartmentDao departmentDao = new DepartmentDao();

    public static void main(String[] args) {

        // 1. Create random departments
        List<Department> companyDepartments = getCompanyDepartments();
        departmentDao.create(companyDepartments);
        List<Department> createdDepartments = departmentDao.findAll();

        // 2. Create random objects.
        List<Employee> randomEmployees = getRandomEmployees();

        // 3. Link company departments with the employees
        for(int i = 0; i< randomEmployees.size(); i++) {
            randomEmployees.get(i).setDepartment(createdDepartments.get(i % createdDepartments.size()));
        }

        // 4. Save random objects
        employeeDao.create(randomEmployees);

        // 5. Create single object
        employeeDao.create(new Employee("John", "Smith", LocalDate.of(999, 9, 9), 200.0));

        // 6. Update single object
//        employeeDao.update(1, "UPDATED NAME", "UPDATED SURNAME", LocalDate.of(1999, 9, 9));

        // 7. Display the updated values
        List<Employee> updatedEmployees = employeeDao.findAll();
        for (Employee emp: updatedEmployees) {
            System.out.println(emp);
        }

        // 8. Delete object by ID
        // Note: this call will also delete the address from the address table
        employeeDao.delete(5);

        // 9. Close hibernate
        HibernateUtil.shutdown();
    }

    /**
     * This method generates
     * @return
     */
    private static List<Employee> getRandomEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Employee employee = new Employee("John " + i, "Smith " + i, LocalDate.of(i, 10, 10), 1500.0 * (i + 1));
            Address address = new Address("V.Alecsandri " + i, "Chisinau", "Moldova", "MD" + (2000 + i));
            employee.setAddress(address);
            employeeList.add(employee);
        }
        return employeeList;
    }

    private static List<Department> getCompanyDepartments() {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department("IT", "Location of IT department"));
        departments.add(new Department("Finance", "Location of Finance department"));
        departments.add(new Department("Administration", "Location of Administration department"));
        departments.add(new Department("Human resources", "Location of Human resources department"));
        return departments;
    }


}
