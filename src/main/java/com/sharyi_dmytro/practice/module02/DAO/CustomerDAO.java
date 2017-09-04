package com.sharyi_dmytro.practice.module02.DAO;

import com.sharyi_dmytro.practice.module02.Entities.Company;
import com.sharyi_dmytro.practice.module02.Entities.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDAO extends MainDAO<Customer, Integer> {

    public void create(Customer entity) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            session.save(entity);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Customer read(Integer key) {
        Customer customer = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {

            transaction = session.getTransaction();

            transaction.begin();

            customer = session.get(Customer.class, key);

            if (customer == null) return null;


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return customer;
    }

    public void update(Customer entity) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            session.merge(entity);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void delete(Customer entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            session.delete(entity);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public List<Customer> getAll() {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Customer> customers = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            Query<Customer> namedQuery = session.createNamedQuery("Customer.getAll", Customer.class);

            transaction.commit();

            customers = namedQuery.getResultList();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return customers;
    }
}