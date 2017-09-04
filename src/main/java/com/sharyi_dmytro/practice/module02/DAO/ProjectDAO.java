package com.sharyi_dmytro.practice.module02.DAO;

import com.sharyi_dmytro.practice.module02.Entities.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
public class ProjectDAO extends MainDAO<Project, Integer> {

    public void create(Project entity) {

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

    public Project read(Integer key) {
        Project project = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {

            transaction = session.getTransaction();

            transaction.begin();

            project = session.get(Project.class, key);

            if (project == null) return null;


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return project;
    }

    public void update(Project entity) {

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

    public void delete(Project entity) {
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

    public List<Project> getAll() {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Project> projects = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            Query<Project> namedQuery = session.createNamedQuery("Project.getAll", Project.class);

            transaction.commit();

            projects = namedQuery.getResultList();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return projects;
    }
}
