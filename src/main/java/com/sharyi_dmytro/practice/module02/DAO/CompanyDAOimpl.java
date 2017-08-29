package com.sharyi_dmytro.practice.module02.DAO;


import com.sharyi_dmytro.practice.module02.DB.PoolConnections;
import com.sharyi_dmytro.practice.module02.Entities.Company;
import com.sharyi_dmytro.practice.module02.Entities.Developer;
import com.sharyi_dmytro.practice.module02.Entities.Project;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOimpl extends PoolConnections implements CompanyDAO  {

    public CompanyDAOimpl(DataSource dataSource) {
        super(dataSource);
    }

    public void create(String name) {
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO companies (company_name) VALUES (?)");
            preparedStatement.setString(1, name);
            preparedStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Company read(int id) {

        Company company = new Company();


        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT companies.idCompany,company_name, GROUP_CONCAT(projects.project_name  SEPARATOR',') AS projects" +
                    " FROM companies JOIN projects ON companies.idCompany = projects.Companies_id WHERE companies.idCompany = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ArrayList<Project> projects = null;

                company.setName(resultSet.getString("company_name"));
                company.setId(resultSet.getInt("idCompany"));

                String projs = resultSet.getString("projects");
                if (projs != null) {
                    projects = new ArrayList<>();
                    for (String s : projs.split(",")) {
                        Project project = new Project();

                        project.setProjectName(s);
                        projects.add(project);

                    }
                }

                company.setProjects(projects);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return company;
    }

    public void update(int id, String newName) {
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE companies SET company_name = ? WHERE idCompany = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM companies WHERE idCompany = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Company> getAll() {

        List<Company> companies = new ArrayList<>();

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT companies.idCompany,companies.company_name, GROUP_CONCAT(projects.project_name  SEPARATOR',') AS projects " +
                    "FROM companies LEFT JOIN projects ON companies.idCompany = projects.Companies_id GROUP BY companies.idCompany");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Company company = new Company();
                ArrayList<Project> projects = new ArrayList<>();

                company.setId(resultSet.getInt("idCompany"));
                company.setName(resultSet.getString("company_name"));

                String projectsName = resultSet.getString("projects");
                if (projectsName != null) {
                    for (String name : projectsName.split(",")) {
                        Project project = new Project();
                        project.setProjectName(name);
                        projects.add(project);

                        company.setProjects(projects);
                    }
                }


                companies.add(company);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }
}
