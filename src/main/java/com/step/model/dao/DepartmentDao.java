package com.step.model.dao;

import com.step.hibernate.HibernateUtil;
import com.step.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DepartmentDao {

    public List<Department> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query<Department> query = session.createQuery("FROM Department ", Department.class);
        List<Department> departments = query.list();
        tx.commit();
        session.close();
        return departments;
    }

    public Department findById(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Department department = session.get(Department.class, id);
        tx.commit();
        session.close();
        return department;
    }

    public List<Department> findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query<Department> query = session.createQuery("FROM Department WHERE name = :name", Department.class);
        query.setParameter("name", name);
        List<Department> departments = query.list();
        tx.commit();
        session.close();
        return departments;
    }

    public void create(String name, String location) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Department emp = new Department(name, location);
        session.save(emp);
        tx.commit();
        session.close();
    }

    public void create(List<Department> departments) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for (int index = 0; index < departments.size(); index++) {
            session.save(departments.get(index));
            if(index % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
        tx.commit();
        session.close();
    }

    public Department update(Department department) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(department);
        tx.commit();
        session.close();
        return department;
    }

    public Department delete(Integer id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Department department = session.get(Department.class, id);
        session.delete(department);
        tx.commit();
        session.close();
        return department;
    }
}
