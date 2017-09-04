package com.sharyi_dmytro.practice.module02.DAO;

import com.sharyi_dmytro.practice.module02.SessionFactory.HibernateUtil;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;


public abstract class MainDAO<T, K extends Serializable> {

    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public abstract void create(T entity);

    public abstract T read(K key);

    public abstract void update(T entity);

    public abstract void delete(T entity);

    public abstract List<T> getAll();


}
