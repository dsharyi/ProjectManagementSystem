package com.sharyi_dmytro.practice.module02.DAO;

import com.sharyi_dmytro.practice.module02.Entities.Customer;
import com.sharyi_dmytro.practice.module02.Entities.Project;

import java.util.List;


public interface CustomerDAO {
    void create(String name);

    Customer read(int id);

    void updade(int id, String newName);

    void delete(int id);

    List<Customer> getAll();

}
