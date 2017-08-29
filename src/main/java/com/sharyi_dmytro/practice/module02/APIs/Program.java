package com.sharyi_dmytro.practice.module02.APIs;

import com.sharyi_dmytro.practice.module02.Controller.*;
import com.sharyi_dmytro.practice.module02.Entities.*;
import com.sharyi_dmytro.practice.module02.Exceptions.SkillNull;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.util.List;

/**
 * Created by nonal on 14.07.2017.
 */
public class Program implements API {
    private SkillController skillController;
    private DeveloperController developerController;
    private CustomerController customerController;
    private CompanyController companyController;
    private ProjectController projectController;

    public Program(SkillController skillsController, DeveloperController developerController, CustomerController customerController, CompanyController companyController, ProjectController projectController) {
        this.skillController = skillsController;
        this.developerController = developerController;
        this.customerController = customerController;
        this.companyController = companyController;
        this.projectController = projectController;
    }


    @Override
    public boolean createDeveloper(String name, String secondName, int salary, String projectName, List<String> skills) throws SkillNull {
        return developerController.create(name, secondName, salary, projectName, skills);
    }

    @Override
    public boolean updateDeveloper(int id, String newName, String newSecondName, int newSalary, List<String> skills) throws WrongId {
        return developerController.update(id, newName, newSecondName, newSalary, skills);
    }

    @Override
    public Developer readDeveloper(int id) throws WrongId {
        return developerController.read(id);
    }

    @Override
    public boolean deleteDeveloper(int id) throws WrongId {
        return developerController.delete(id);
    }

    @Override
    public void showAllDevelopers() {
        developerController.showAllDevelopers();
    }

    @Override
    public boolean createSkill(String nameSkill) {
        return skillController.create(nameSkill);
    }

    @Override
    public Skill readSkill(int id) throws WrongId {
        return skillController.read(id);
    }

    @Override
    public boolean updateSkill(int id, String newName) throws WrongId {
        return skillController.update(id, newName);
    }

    @Override
    public boolean deleteSkill(int id) throws WrongId {
        return skillController.delete(id);
    }

    @Override
    public void showAllSkills() {
        skillController.showAllSkills();
    }

    @Override
    public boolean createCustomer(String name) {
        return customerController.create(name);
    }

    @Override
    public Customer readCustomer(int id) throws WrongId {
        return customerController.read(id);
    }

    @Override
    public boolean updateCustomer(int id, String newName) throws WrongId {
        return customerController.updade(id, newName);
    }

    @Override
    public boolean deleteCustomer(int id) throws WrongId {
        return customerController.delete(id);
    }

    @Override
    public void showAllCustomers() {
        customerController.showAllCustomers();
    }

    @Override
    public boolean createCompany(String name) {
        return companyController.create(name);
    }

    @Override
    public Company readCompany(int id) throws WrongId {
        return companyController.read(id);
    }

    @Override
    public boolean updateCompany(int id, String newName) throws WrongId {
        return companyController.updade(id, newName);
    }

    @Override
    public boolean deleteCompany(int id) throws WrongId {
        return companyController.delete(id);
    }

    @Override
    public void showAllCompanies() {
        companyController.showAllCompanies();
    }

    @Override
    public boolean createProject(String name, int cost, int idCompany, int idCustomer) throws WrongId {
        return projectController.create(name, cost, idCompany, idCustomer);
    }

    @Override
    public Project readProject(int id) throws WrongId {
        return projectController.read(id);
    }

    @Override
    public boolean updateProject(int id, String newName, int newCost) throws WrongId {
        return projectController.update(id, newName, newCost);
    }

    @Override
    public boolean deleteProject(int id) throws WrongId {
        return projectController.delete(id);
    }

    @Override
    public void showAllProjects() {
        projectController.showAll();
    }
}