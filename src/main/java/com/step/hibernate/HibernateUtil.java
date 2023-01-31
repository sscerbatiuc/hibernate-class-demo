package com.step.hibernate;

import com.step.model.Address;
import com.step.model.Department;
import com.step.model.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {

    private final static SessionFactory factory = buildSessionFactory();


    public static SessionFactory getSessionFactory() { return factory; }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configure = new Configuration().configure(new File("src\\main\\resources\\hibernate.cfg.xml"));

            // Configure the entities
            configure.addAnnotatedClass(Employee.class);
            configure.addAnnotatedClass(Address.class);
            configure.addAnnotatedClass(Department.class);

            return configure.buildSessionFactory();
        } catch (Exception ex) {
            System.err.println("Could not configure Hibernate. " + ex.getMessage());
            throw new RuntimeException("Hibernate configuration failed. Cannot start application!");
        }
    }

    public static void shutdown() {
        factory.close();
    }

}
