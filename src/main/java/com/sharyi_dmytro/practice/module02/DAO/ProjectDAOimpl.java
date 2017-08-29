package com.sharyi_dmytro.practice.module02.DAO;

import com.sharyi_dmytro.practice.module02.DB.PoolConnections;
import com.sharyi_dmytro.practice.module02.Entities.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProjectDAOimpl extends PoolConnections implements ProjectDAO {
    public ProjectDAOimpl(DataSource dataSource) {
        super(dataSource);
    }

    public void create(String nameProj, int cost, int idCompany, int idCustomer) {
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO projects(project_name, cost, Companies_id, Customers_id) VALUES (?,?,?,?)");
            preparedStatement.setString(1, nameProj);
            preparedStatement.setInt(2, cost);
            preparedStatement.setInt(3, idCompany);
            preparedStatement.setInt(4, idCustomer);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Project read(int id) {

        Project project = null;
        Company company = new Company();
        Customer customer = new Customer();

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idProject,project_name,cost,companies.company_name AS company,customers.customer_name AS customer FROM projects" +
                    " JOIN companies ON projects.Companies_id = companies.idCompany JOIN customers ON projects.Customers_id = customers.idCustomer WHERE idProject = ?");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                project = new Project();
                project.setId(resultSet.getInt("idProject"));
                project.setProjectName(resultSet.getString("project_name"));
                project.setProjectCost(resultSet.getInt("cost"));

                customer.setName(resultSet.getString("customer"));
                project.setCustomer(customer);

                company.setName(resultSet.getString("company"));
                project.setCompany(company);

            }
            if (project != null) {
                ArrayList<Developer> developers = new ArrayList<>();

                preparedStatement = connection.prepareStatement("SELECT idDev, name,surname  FROM developers WHERE Projects_id = ?");
                preparedStatement.setInt(1, id);
                ResultSet resultSet1 = preparedStatement.executeQuery();

                while (resultSet1.next()) {
                    Developer developer = new Developer();
                    developer.setId(resultSet1.getInt("idDev"));
                    developer.setName(resultSet1.getString("name"));
                    developer.setSurname(resultSet1.getString("surname"));

                    developers.add(developer);
                }
                project.setDevelopers(developers);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return project;
    }

    public void update(int id, String newName, int cost) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE projects SET project_name = ?, cost = ? WHERE idProject = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, cost);
            preparedStatement.setInt(3, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM projects WHERE idProject = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Project> getAll() {

        List<Project> projects = new ArrayList<>();

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idProject,project_name,cost,companies.company_name,customers.customer_name, GROUP_CONCAT(developers.surname SEPARATOR ',') AS developers FROM  projects JOIN companies ON projects.Companies_id = companies.idCompany  JOIN customers ON projects.Customers_id = customers.idCustomer LEFT JOIN developers ON (projects.idProject = developers.Projects_id) GROUP BY idProject");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Project project = new Project();
                Customer customer = new Customer();
                Company company = new Company();
                ArrayList<Developer> developers = new ArrayList<>();

                customer.setName(resultSet.getString("customer_name"));

                company.setName(resultSet.getString("company_name"));

                String developersSecondName = resultSet.getString("developers");
                if (developersSecondName != null) {
                    for (String s : developersSecondName.split(",")) {
                        Developer developer = new Developer();
                        developer.setSurname(s);

                        developers.add(developer);
                    }
                }

                project.setId(resultSet.getInt("idProject"));
                project.setProjectName(resultSet.getString("project_name"));
                project.setProjectCost(resultSet.getInt("cost"));
                project.setCustomer(customer);
                project.setCompany(company);
                project.setDevelopers(developers);

                projects.add(project);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }
}





