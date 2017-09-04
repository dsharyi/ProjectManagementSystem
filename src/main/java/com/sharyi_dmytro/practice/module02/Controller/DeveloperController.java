package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.Entities.Developer;
import com.sharyi_dmytro.practice.module02.Entities.Project;
import com.sharyi_dmytro.practice.module02.Entities.Skill;
import com.sharyi_dmytro.practice.module02.Exceptions.SkillNull;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.util.List;

/**
 * Created by nonal on 30.07.2017.
 */
public interface DeveloperController {

    boolean create (String name, String secondName, int salary, Project projectName, List<Skill> skills);

    boolean  update(int id, String newName, String newSecondName, int newSalary,Project project, List<Skill> skills) throws WrongId;

    Developer read(int id) throws WrongId;

    boolean  delete (int id) throws WrongId;

    List<Developer> showAllDevelopers();

}
