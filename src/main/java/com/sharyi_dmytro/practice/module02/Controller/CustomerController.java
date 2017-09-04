package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.Entities.Customer;
import com.sharyi_dmytro.practice.module02.Entities.Project;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.util.List;


public interface CustomerController {

    boolean create(String name);

    Customer read(int id) throws WrongId;

    boolean update(int id, String newName) throws WrongId;

    boolean delete(int id) throws WrongId;

    List<Customer> showAllCustomers();

}

