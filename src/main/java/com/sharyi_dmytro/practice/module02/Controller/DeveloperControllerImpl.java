package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.DAO.DeveloperDAO;
import com.sharyi_dmytro.practice.module02.DAO.MainDAO;
import com.sharyi_dmytro.practice.module02.Entities.Developer;
import com.sharyi_dmytro.practice.module02.Entities.Project;
import com.sharyi_dmytro.practice.module02.Entities.Skill;
import com.sharyi_dmytro.practice.module02.Exceptions.SkillNull;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.util.List;

/**
 * Created by nonal on 30.07.2017.
 */
public class DeveloperControllerImpl implements DeveloperController {


    MainDAO<Developer, Integer> developerDao;

    public DeveloperControllerImpl(MainDAO<Developer, Integer> commonDaoImpl) {
        this.developerDao = commonDaoImpl;
    }

    public boolean create(String name, String secondName, int salary, Project project, List<Skill> skills) {
        Developer developer = new Developer();
        developer.setSalary(salary);
        developer.setName(name);
        developer.setSecondName(secondName);
        developer.setProject(project);
        developer.setSkills(skills);

        developerDao.create(developer);

        return true;
    }

    public boolean update(int id, String newName, String newSecondName, int newSalary,Project project, List<Skill> skills) throws WrongId {
        if (developerDao.getAll().stream().noneMatch(developer -> developer.getId() == id))
            throw new WrongId("Разработчика с таким ID не существует, повторите ввод");

        Developer developer = new Developer();
        developer.setId(id);
        developer.setSalary(newSalary);
        developer.setName(newName);
        developer.setSecondName(newSecondName);
        developer.setProject(project);
        developer.setSkills(skills);
        developerDao.update(developer);

        return true;
    }

    public Developer read(int id) throws WrongId {
        if (developerDao.getAll().stream().noneMatch(developer -> developer.getId() == id))
            throw new WrongId("Разработчика с таким ID не существует, повторите ввод");

        return developerDao.read(id);
    }

    public boolean delete(int id) throws WrongId {
        if (developerDao.getAll().stream().noneMatch(developer -> developer.getId() == id))
            throw new WrongId("Разработчика с таким ID не существует, повторитее ввод");

        Developer developer = developerDao.read(id);

        developerDao.delete(developer);

        return true;
    }

    public List<Developer> showAllDevelopers() {
        return developerDao.getAll();
    }
}
