package com.sharyi_dmytro.practice.module02.APIs;


import com.sharyi_dmytro.practice.module02.Entities.*;
import com.sharyi_dmytro.practice.module02.Exceptions.IncorrectName;
import com.sharyi_dmytro.practice.module02.Exceptions.SkillNull;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.util.List;

public interface API {

    boolean createDeveloper(String name, String secondName, int salary, Project projectName, List<Skill> skills) throws SkillNull;

    boolean updateDeveloper(int id, String newName, String newSecondName, int newSalary,Project project, List<Skill> skills) throws WrongId;

    Developer readDeveloper(int id) throws WrongId;

    boolean deleteDeveloper(int id) throws WrongId;

    void showAllDevelopers();

    boolean createSkill(String nameSkill) throws SkillNull;

    Skill readSkill(int id) throws WrongId;

    boolean updateSkill(int id, String newName) throws WrongId;

    boolean deleteSkill(int id) throws WrongId;

    void showAllSkills();

    boolean createCustomer(String name);

    Customer readCustomer(int id) throws WrongId;

    boolean updateCustomer(int id, String newName) throws WrongId;

    boolean deleteCustomer(int id) throws WrongId;

    void showAllCustomers();

    boolean createCompany(String name);

    Company readCompany(int id) throws WrongId;

    boolean updateCompany(int id, String newName) throws WrongId;

    boolean deleteCompany(int id) throws WrongId;

    void showAllCompanies();

    boolean createProject(String name, int cost, Company company, Customer customer) throws WrongId;

    Project readProject(int id) throws WrongId;

    boolean updateProject(int id, String newName, int newCost) throws WrongId;

    boolean deleteProject(int id) throws WrongId;

    void showAllProjects();

    Project findProjectByName(String nameProject) throws IncorrectName;

    Skill findSkillByName(String skillName) throws IncorrectName;
}

