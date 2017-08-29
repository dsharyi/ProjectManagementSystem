package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.DAO.CompanyDAO;
import com.sharyi_dmytro.practice.module02.Entities.Company;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

/**
 * Created by nonal on 31.07.2017.
 */
public class CompanyControllerImpl implements CompanyController {

    CompanyDAO daoCompany;

    public CompanyControllerImpl(CompanyDAO daoCompany) {
        this.daoCompany = daoCompany;
    }

    public boolean create(String name) {
        daoCompany.create(name);
        return true;
    }

    public Company read(int id) throws WrongId {
        if (daoCompany.getAll().stream().map(Company::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Компании с таким ID не существует, повторите ввод!");
        return daoCompany.read(id);
    }

    public boolean updade(int id, String newName) throws WrongId {
        if (daoCompany.getAll().stream().map(Company::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Компании с таким ID не существует, повторите ввод!");
        daoCompany.update(id, newName);
        return true;
    }

    public boolean delete(int id) throws WrongId {
        if (daoCompany.getAll().stream().map(Company::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Компании с таким ID не существует, повторите ввод!");
        daoCompany.delete(id);
        return true;
    }

    public void showAllCompanies() {
        daoCompany.getAll().forEach(System.out::println);
    }
}
