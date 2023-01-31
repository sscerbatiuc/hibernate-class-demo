package com.step.services;

import com.step.model.Department;
import com.step.model.Employee;
import com.step.model.dao.DepartmentDao;

import java.util.ArrayList;
import java.util.List;

public class DepartmentService {

    private final DepartmentDao departmentDao = new DepartmentDao();

    public List<Department> getCompanyDepartments() {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department("IT", "Location of IT department"));
        departments.add(new Department("Finance", "Location of Finance department"));
        departments.add(new Department("Administration", "Location of Administration department"));
        departments.add(new Department("Human resources", "Location of Human resources department"));
        return departments;
    }

    public List<Department> createRandomCompanyDepartments() {
        departmentDao.create(getCompanyDepartments());
        return departmentDao.findAll();
    }

    public void printAll() {
        List<Department> departments = departmentDao.findAll();
        for (Department dep: departments) {
            System.out.println(dep);
        }
    }
}
