package com.sharyi_dmytro.practice.module02.Launcher;


import com.sharyi_dmytro.practice.module02.APIs.API;
import com.sharyi_dmytro.practice.module02.APIs.Program;
import com.sharyi_dmytro.practice.module02.Console.*;
import com.sharyi_dmytro.practice.module02.Controller.*;
import com.sharyi_dmytro.practice.module02.DAO.*;
import com.sharyi_dmytro.practice.module02.Entities.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        MainDAO<Project, Integer> projectDao = new ProjectDAO();
        MainDAO<Company, Integer> companyDao = new CompanyDAO();
        MainDAO<Customer, Integer> customerDao = new CustomerDAO();
        MainDAO<Developer, Integer> developerDao = new DeveloperDAO();
        MainDAO<Skill, Integer> skillDao = new SkillDAO();

        SkillController skillController = new SkillControllerImpl(skillDao);
        CompanyController companyController = new CompanyControllerImpl(companyDao);
        CustomerController customerController = new CustomerControllerImpl(customerDao);
        ProjectController projectController = new ProjectControllerImpl(projectDao);
        DeveloperController developerController = new DeveloperControllerImpl(developerDao);

        API api = new Program(skillController,
                developerController,
                customerController,
                companyController,
                projectController);

        ConsoleHelper consoleHelper = new ConsoleHelper(bufferedReader,
                new ConsoleDeveloper(bufferedReader, api),
                new ConsoleSkill(bufferedReader, api),
                new ConsoleCompany(bufferedReader, api),
                new ConsoleCustomer(bufferedReader, api),
                new ConsoleProject(bufferedReader, api));

        consoleHelper.chooseOperation();

        bufferedReader.close();


    }
}