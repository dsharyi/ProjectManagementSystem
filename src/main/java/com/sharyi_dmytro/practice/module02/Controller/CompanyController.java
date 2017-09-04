package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.Entities.Company;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.util.List;


public interface CompanyController {


    boolean create(String name);

    Company read(int id) throws WrongId;

    boolean updade(int id, String newName) throws WrongId;

    boolean delete(int id) throws WrongId;

    List<Company> showAllCompanies();

}