package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.DAO.CustomerDAO;
import com.sharyi_dmytro.practice.module02.Entities.Customer;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

/**
 * Created by nonal on 18.07.2017.
 */
public class CustomerControllerImpl implements CustomerController {

    private CustomerDAO customerDAO;

    public CustomerControllerImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public boolean create(String name) {
        customerDAO.create(name);
        return true;
    }

    public Customer read(int id) throws WrongId {
        if (customerDAO.getAll().stream().map(Customer::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Заказчика с таким ID не существует, повторите ввод!");
        return customerDAO.read(id);
    }

    public boolean updade(int id, String newName) throws WrongId {
        if (customerDAO.getAll().stream().map(Customer::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Заказчика с таким ID не существует, повторите ввод!");
        customerDAO.updade(id, newName);
        return true;
    }

    public boolean delete(int id) throws WrongId {
        if (customerDAO.getAll().stream().map(Customer::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Заказчика с таким ID не существует, повторите ввод!");
        customerDAO.delete(id);
        return true;
    }

    public void showAllCustomers() {
        customerDAO.getAll().forEach(System.out::println);
    }
}


