package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.DAO.DeveloperDAO;
import com.sharyi_dmytro.practice.module02.Entities.Developer;
import com.sharyi_dmytro.practice.module02.Exceptions.SkillNull;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.util.List;

/**
 * Created by nonal on 30.07.2017.
 */
public class DeveloperControllerImpl implements DeveloperController {


    private DeveloperDAO daoDeveloper;

    public DeveloperControllerImpl(DeveloperDAO daoDeveloper) {
        this.daoDeveloper = daoDeveloper;
    }

    public boolean create(String name, String secondName, int salary, String projectName, List<String> skills) throws SkillNull {
        daoDeveloper.create(name, secondName, salary, projectName, skills);
        return true;
    }

    public boolean update(int id, String newName, String newSecondName, int newSalary, List<String> skills) throws WrongId {
        if (daoDeveloper.getAll().stream().map(Developer::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Разработчика с таким ID не существует, повторите ввод!");
        daoDeveloper.update(id, newName, newSecondName, newSalary, skills);
        return true;
    }

    public Developer read(int id) throws WrongId {
        if (daoDeveloper.getAll().stream().map(Developer::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Разработчика с таким ID не существует, повторите ввод!");
        return daoDeveloper.read(id);
    }

    public boolean delete(int id) throws WrongId {
        if (daoDeveloper.getAll().stream().map(Developer::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Разработчика с таким ID не существует, повторите ввод!");
        daoDeveloper.delete(id);
        return true;
    }

    public void showAllDevelopers() {
        daoDeveloper.getAll().forEach(System.out::println);
    }


}
