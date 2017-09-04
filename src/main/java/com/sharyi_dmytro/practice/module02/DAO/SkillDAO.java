package com.sharyi_dmytro.practice.module02.DAO;

import com.sharyi_dmytro.practice.module02.Entities.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class SkillDAO extends MainDAO<Skill, Integer> {

    public void create(Skill entity) {

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

    public Skill read(Integer key) {
        Skill skill = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {

            transaction = session.getTransaction();

            transaction.begin();

            skill = session.get(Skill.class, key);

            if (skill == null) return null;


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return skill;
    }

    public void update(Skill entity) {

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

    public void delete(Skill entity) {
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

    public List<Skill> getAll() {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Skill> skills = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            Query<Skill> namedQuery = session.createNamedQuery("Skill.getAll", Skill.class);

            transaction.commit();

            skills = namedQuery.getResultList();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return skills;
    }
}

