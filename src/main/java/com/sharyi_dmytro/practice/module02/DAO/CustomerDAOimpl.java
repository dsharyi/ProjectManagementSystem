package com.sharyi_dmytro.practice.module02.DAO;

import com.sharyi_dmytro.practice.module02.DB.PoolConnections;
import com.sharyi_dmytro.practice.module02.Entities.Customer;
import com.sharyi_dmytro.practice.module02.Entities.Developer;
import com.sharyi_dmytro.practice.module02.Entities.Project;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAOimpl extends PoolConnections implements CustomerDAO {

    public CustomerDAOimpl(DataSource dataSource) {
        super(dataSource);
    }

    public void create(String name) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers(customer_name) VALUES (?)");
            preparedStatement.setString(1, name);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Customer read(int id) {

        Customer customer = new Customer();
        ArrayList<Project> projectList = null;

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT customers.idCustomer,customers.customer_name, GROUP_CONCAT(projects.project_name SEPARATOR',') AS  projects\n" +
                            "FROM customers\n" +
                            "LEFT JOIN projects ON customers.idCustomer=projects.Customers_id WHERE customers.idCustomer = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer.setId(resultSet.getInt("idCustomer"));
                customer.setName(resultSet.getString("customer_name"));

                String projects = resultSet.getString("projects");
                if (projects != null) {
                    projectList = new ArrayList<>();
                    for (String proj : projects.split(",")) {
                        Project project = new Project();
                        project.setProjectName(proj);

                        projectList.add(project);

                    }
                    customer.setProjects(projectList);
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void updade(int id, String newName) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customers SET customer_name = ? WHERE idCustomer = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE idCustomer = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Customer> getAll() {

        ArrayList<Customer> customers = new ArrayList<>();

        try (Connection connection = getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT customers.idCustomer,customers.customer_name,GROUP_CONCAT(projects.project_name SEPARATOR',') AS projects FROM  customers " +
                    "LEFT JOIN projects ON customers.idCustomer = projects.Customers_id GROUP BY customers.idCustomer");

            while (resultSet.next()) {

                Customer customer = new Customer();
                ArrayList<Project> projects = new ArrayList<>();

                int idCustomer = resultSet.getInt("idCustomer");
                String name = resultSet.getString("customer_name");
                String projectsName = resultSet.getString("projects");

                customer.setId(idCustomer);
                customer.setName(name);

                if (projectsName != null) {

                    for (String proj : projectsName.split(",")) {
                        Project project = new Project();
                        project.setProjectName(proj);
                        projects.add(project);

                        customer.setProjects(projects);
                    }
                }

                customers.add(customer);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}


