package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.Entities.Company;
import com.sharyi_dmytro.practice.module02.Entities.Customer;
import com.sharyi_dmytro.practice.module02.Entities.Project;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.util.List;

/**
 * Created by nonal on 27.07.2017.
 */
public interface ProjectController {

    boolean create(String name, int cost, Company company, Customer customer);

    Project read(int id) throws WrongId;

    boolean update(int id, String newName, int newCost) throws WrongId;

    boolean delete(int id) throws WrongId;

    List<Project> showAll();

}
