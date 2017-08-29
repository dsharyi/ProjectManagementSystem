package com.sharyi_dmytro.practice.module02.DAO;

import com.sharyi_dmytro.practice.module02.Entities.Developer;
import com.sharyi_dmytro.practice.module02.Exceptions.SkillNull;

import java.util.List;


public interface DeveloperDAO {
    void create(String name, String secondName, int salary, String projectName, List<String> skills) throws SkillNull;

    void update(int id, String newName, String newSecondName, int newSalary, List<String> skills);

    Developer read(int id);

    void delete(int id);

    List<Developer> getAll();

}
