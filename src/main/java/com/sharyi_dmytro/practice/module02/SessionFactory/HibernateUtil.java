package com.sharyi_dmytro.practice.module02.SessionFactory;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import java.io.File;


public class HibernateUtil {
    private static final SessionFactory mySessionFactory;

    private HibernateUtil() {
    }

    static {
        try {
            File file = new File("C:\\Users\\nonal\\IdeaProjects\\ProjectManagementSystem\\src\\main\\resources\\META-INF\\hibernate.cfg.xml");
            Configuration configuration = new Configuration();
            configuration.configure(file);

            mySessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() throws HibernateException {

        return mySessionFactory;
    }
}
