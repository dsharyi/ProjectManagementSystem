package com.sharyi_dmytro.practice.module02.Controller;

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

    private ProjectDAO daoProject;

    public ProjectControllerImpl(ProjectDAO daoProject) {
        this.daoProject = daoProject;
    }

    public boolean create(String name, int cost, int idCompany, int idCustomers) {
        daoProject.create(name, cost, idCompany, idCustomers);
        return true;
    }

    public Project read(int id) throws WrongId {
        if (daoProject.getAll().stream().map(Project::getId).noneMatch(integer -> id == integer))
            throw new WrongId("Проекта с таким id не существует, повторите ввод!");
        return daoProject.read(id);
    }

    public boolean update(int id, String newName, int newCost) throws WrongId {
        if (daoProject.getAll().stream().map(Project::getId).noneMatch(integer -> id == integer))
            throw new WrongId("Проекта с таким id не существует, повторите ввод!");

        daoProject.update(id, newName, newCost);
        return true;
    }

    public boolean delete(int id) throws WrongId {
        if (daoProject.getAll().stream().map(Project::getId).noneMatch(integer -> id == integer))
            throw new WrongId("Проекта с таким id не существует, повторите ввод!");
        daoProject.delete(id);
        return true;
    }


    public void showAll() {
        daoProject.getAll().forEach(System.out::println);
    }
}

