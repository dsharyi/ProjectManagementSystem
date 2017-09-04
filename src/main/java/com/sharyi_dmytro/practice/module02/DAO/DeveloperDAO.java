package com.sharyi_dmytro.practice.module02.DAO;

import com.sharyi_dmytro.practice.module02.Entities.Developer;
import com.sharyi_dmytro.practice.module02.Exceptions.SkillNull;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class DeveloperDAO extends MainDAO<Developer, Integer> {

    public void create(Developer entity) {

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

    public Developer read(Integer key) {
        Developer developer = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {

            transaction = session.getTransaction();

            transaction.begin();

            developer = session.get(Developer.class, key);

            if (developer == null) return null;


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return developer;
    }

    public void update(Developer entity) {

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

    public void delete(Developer entity) {
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

    public List<Developer> getAll() {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Developer> developers = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            Query<Developer> namedQuery = session.createNamedQuery("Developer.getAll", Developer.class);

            transaction.commit();

            developers = namedQuery.getResultList();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return developers;
    }
}
