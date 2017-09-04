package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.DAO.CustomerDAO;
import com.sharyi_dmytro.practice.module02.DAO.MainDAO;
import com.sharyi_dmytro.practice.module02.Entities.Customer;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.util.List;

/**
 * Created by nonal on 18.07.2017.
 */
public class CustomerControllerImpl implements CustomerController {

    private MainDAO<Customer, Integer> customerDao;

    public CustomerControllerImpl(MainDAO<Customer, Integer> customerDao) {
        this.customerDao = customerDao;
    }

    public boolean create(String name) {

        Customer customer = new Customer();
        customer.setName(name);
        customerDao.create(customer);

        return true;
    }

    public Customer read(int id) throws WrongId {
        if (customerDao.getAll().stream().noneMatch(customer -> customer.getIdCustomer() == id))
            throw new WrongId("Заказчика с таким ID не существует, повторите ввод");

        return customerDao.read(id);
    }

    public boolean update(int id, String newName) throws WrongId {
        if (customerDao.getAll().stream().noneMatch(customer -> customer.getIdCustomer() == id))
            throw new WrongId("Заказчика с таким ID не существует, повторите ввод");

        Customer customer = new Customer();
        customer.setIdCustomer(id);
        customer.setName(newName);
        customerDao.update(customer);

        return true;
    }

    public boolean delete(int id) throws WrongId {
        if (customerDao.getAll().stream().noneMatch(customer -> customer.getIdCustomer() == id))
            throw new WrongId("Заказчика с таким ID не существует, повторите ввод");

        Customer customer = read(id);

        customerDao.delete(customer);

        return true;
    }

    public List<Customer> showAllCustomers() {
        return customerDao.getAll();
    }
}

