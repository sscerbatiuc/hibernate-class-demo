package com.step.model;

import com.step.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class EmployeeDao {

    public List<Employee> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query<Employee> query = (Query<Employee>) session.createQuery("FROM Employee");
        List<Employee> employees = query.list();
        tx.commit();
        session.close();
        return employees;
    }

    public Employee findById(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        tx.commit();
        session.close();
        return employee;
    }

    public List<Employee> findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query<Employee> query = (Query<Employee>) session.createQuery("FROM Employee WHERE name = :name");
        query.setParameter("name", name);
        List<Employee> employees = query.list();
        tx.commit();
        session.close();
        return employees;
    }

    public void create(String name, String surname, LocalDate birthdate) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Employee emp = new Employee(name, surname, birthdate);
        session.save(emp);
        tx.commit();
        session.close();
    }

    public void create(List<Employee> employees) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for (int index = 0; index < employees.size(); index++) {
            session.save(employees.get(index));
            if(index % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
        tx.commit();
        session.close();
    }

    public Employee update(Integer id, String name, String surname, LocalDate birthdate) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setBirthdate(birthdate);
        session.update(employee);
        tx.commit();
        session.close();
        return employee;
    }

    public Employee delete(Integer id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.delete(employee);
        tx.commit();
        session.close();
        return employee;
    }
}
