package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.DAO.MainDAO;
import com.sharyi_dmytro.practice.module02.DAO.ProjectDAO;
import com.sharyi_dmytro.practice.module02.Entities.Company;
import com.sharyi_dmytro.practice.module02.Entities.Customer;
import com.sharyi_dmytro.practice.module02.Entities.Project;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nonal on 27.07.2017.
 */
public class ProjectControllerImpl implements ProjectController {

    private MainDAO<Project, Integer> projectDao;

    public ProjectControllerImpl(MainDAO<Project, Integer> projectDao) {
        this.projectDao = projectDao;
    }

    public boolean create(String name, int cost, Company company, Customer customer) {

        Project project = new Project();
        project.setName(name);
        project.setCost(cost);
        project.setCompany(company);
        project.setCustomer(customer);

        projectDao.create(project);

        return true;
    }

    public Project read(int id) throws WrongId {
        if (projectDao.getAll().stream().noneMatch(project -> project.getId() == id))
            throw new WrongId("Проекта с таким ID не существует, повторите ввод");

        return projectDao.read(id);
    }

    public boolean update(int id, String newName, int newCost) throws WrongId {
        if (projectDao.getAll().stream().noneMatch(project -> project.getId() == id))
            throw new WrongId("Проекта с таким ID не существует, повторите ввод");

        Project project = read(id);
        project.setId(id);
        project.setName(newName);
        project.setCost(newCost);

        projectDao.update(project);

        return true;
    }

    public boolean delete(int id) throws WrongId {
        if (projectDao.getAll().stream().noneMatch(project -> project.getId() == id))
            throw new WrongId("Проекта с таким ID не существует, повторите ввод");

        Project project = projectDao.read(id);

        projectDao.delete(project);

        return true;
    }

    public List<Project> showAll() {
        return projectDao.getAll();
    }
}