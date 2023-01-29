package com.step.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {

    private static SessionFactory factory = buildSessionFactory();


    public static SessionFactory getFactory() { return factory; }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configure = new Configuration().configure(new File("src\\main\\resources\\hibernate.cfg.xml"));
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
