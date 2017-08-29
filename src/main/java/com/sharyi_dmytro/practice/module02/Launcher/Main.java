package com.sharyi_dmytro.practice.module02.Launcher;


import com.sharyi_dmytro.practice.module02.APIs.API;
import com.sharyi_dmytro.practice.module02.APIs.Program;
import com.sharyi_dmytro.practice.module02.Console.*;
import com.sharyi_dmytro.practice.module02.Controller.*;
import com.sharyi_dmytro.practice.module02.DAO.*;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        final String URL = "jdbc:mysql://localhost:3306/dev_db?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String USER = "root";
        final String PASSWORD = "root";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(URL);
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASSWORD);
        basicDataSource.setInitialSize(3);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        SkillDAO skillDAO = new SkillDAOimpl(basicDataSource);
        CompanyDAO companyDAO = new CompanyDAOimpl(basicDataSource);
        CustomerDAO customerDAO = new CustomerDAOimpl(basicDataSource);
        ProjectDAO projectDAO = new ProjectDAOimpl(basicDataSource);
        DeveloperDAO developerDAO = new DeveloperDAOimpl(basicDataSource);

        SkillController skillController = new SkillControllerImpl(skillDAO);
        CompanyController companyController = new CompanyControllerImpl(companyDAO);
        CustomerController customerController = new CustomerControllerImpl(customerDAO);
        ProjectController projectController = new ProjectControllerImpl(projectDAO);
        DeveloperController developerController = new DeveloperControllerImpl(developerDAO);

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