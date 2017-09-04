package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.DAO.CompanyDAO;
import com.sharyi_dmytro.practice.module02.DAO.MainDAO;
import com.sharyi_dmytro.practice.module02.Entities.Company;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.util.List;

/**
 * Created by nonal on 31.07.2017.
 */
public class CompanyControllerImpl implements CompanyController {

    private MainDAO<Company, Integer> companyDao;

    public CompanyControllerImpl(MainDAO<Company, Integer> companyDao) {
        this.companyDao = companyDao;
    }

    public boolean create(String name) {

        Company company = new Company();
        company.setName(name);

        companyDao.create(company);

        return true;
    }

    public Company read(int id) throws WrongId {

        return companyDao.read(id);

    }

    public boolean updade(int id, String newName) throws WrongId {
        if (companyDao.getAll().stream().noneMatch(company -> company.getIdCompany() == id))
            throw new WrongId("Компании с таким ID не существует, повторите ввод");

        Company company = new Company();
        company.setIdCompany(id);
        company.setName(newName);

        companyDao.update(company);

        return true;
    }

    public boolean delete(int id) throws WrongId {
        if (companyDao.getAll().stream().noneMatch(company -> company.getIdCompany() == id))
            throw new WrongId("Компании с таким ID не существует, повторите ввод");

        Company company = read(id);

        companyDao.delete(company);

        return true;
    }

    public List<Company> showAllCompanies() {
        return companyDao.getAll();
    }
}