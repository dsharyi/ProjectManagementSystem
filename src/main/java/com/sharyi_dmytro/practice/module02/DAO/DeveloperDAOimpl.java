package com.sharyi_dmytro.practice.module02.DAO;

import com.sharyi_dmytro.practice.module02.DB.PoolConnections;
import com.sharyi_dmytro.practice.module02.Entities.Developer;
import com.sharyi_dmytro.practice.module02.Entities.Project;
import com.sharyi_dmytro.practice.module02.Entities.Skill;
import com.sharyi_dmytro.practice.module02.Exceptions.SkillNull;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DeveloperDAOimpl extends PoolConnections implements DeveloperDAO {
    public DeveloperDAOimpl(DataSource dataSource) {
        super(dataSource);
    }

    public void create(String name, String surname, int salary, String projectName, List<String> skills) throws SkillNull {
        try (Connection connection = getConnection()) {

            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idProject FROM projects WHERE project_name = ?");
            preparedStatement.setString(1, projectName);
            ResultSet resultSet = preparedStatement.executeQuery();
            int idProject = 0;
            while (resultSet.next()) {
                idProject = resultSet.getInt(1);
            }

            preparedStatement = connection.prepareStatement("INSERT INTO developers (name, surname, salary,Projects_id) VALUES (?,?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, salary);
            preparedStatement.setInt(4,idProject );
            preparedStatement.execute();


            preparedStatement = connection.prepareStatement("SELECT LAST_INSERT_ID() FROM  developers LIMIT 1");
            ResultSet resultSet0 = preparedStatement.executeQuery();
            int lastIdDeveloper = 0;
            while (resultSet0.next()) {

                lastIdDeveloper = resultSet0.getInt(1);
            }

            String dontExistSkills = "";
            for (String oneSkill : skills) {
                int skillID = 0;

                Statement statement = connection.createStatement();
                ResultSet resultSet1 = statement.executeQuery("SELECT skills.idSkill FROM skills WHERE skills.skills ='" + oneSkill + "'");

                while (resultSet1.next()) {
                    skillID = resultSet1.getInt(1);

                }

                statement = connection.createStatement();
                try {
                    statement.execute("INSERT INTO developers_has_skills VALUES (" + lastIdDeveloper + "," + skillID + ")");
                } catch (SQLIntegrityConstraintViolationException s) {
                    dontExistSkills += oneSkill + ", ";
                }

            }

            if (dontExistSkills.length() > 0)
                throw new SkillNull("[" + dontExistSkills + "] - этих навыков нет в списке \"Skills\"");
            connection.commit();


        }catch (SQLIntegrityConstraintViolationException s){
            System.err.println("Неверный формат ввода. Повторите попытку!");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void update(int id, String newName, String newSecondName, int newSalary, List<String> skills) {
        try (Connection connection = getConnection()) {

            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE developers SET name =?, surname=?, salary=? WHERE  idDev = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newSecondName);
            preparedStatement.setInt(3, newSalary);
            preparedStatement.setInt(4, id);
            preparedStatement.execute();


            preparedStatement = connection.prepareStatement("DELETE FROM developers_has_skills WHERE Developers_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            for (String skill : skills) {
                int idSkill = 0;

                preparedStatement = connection.prepareStatement("SELECT idSkill FROM skills WHERE skills = ?");
                preparedStatement.setString(1, skill);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    idSkill = resultSet.getInt(1);
                }

                preparedStatement = connection.prepareStatement("INSERT INTO developers_has_skills VALUES (?,?)");
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, idSkill);
                preparedStatement.execute();

            }


            connection.commit();

        }
        catch (SQLIntegrityConstraintViolationException s){
            System.err.println("Неверный формат ввода. Повторите попытку!");

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Developer read(int id) {

        Developer developer = new Developer();

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT developers.idDev, developers.name, developers.salary, developers.surname,projects.project_name,GROUP_CONCAT(skills.skills SEPARATOR ', ') AS skills  FROM developers " +
                    "JOIN developers_has_skills ON developers.idDev = developers_has_skills.Developers_id JOIN skills ON developers_has_skills.Skills_id = skills.idSkill LEFT JOIN projects ON developers.Projects_id = projects.idProject WHERE developers.idDev = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int idRes = resultSet.getInt("idDev");
                String name = resultSet.getString("name");
                String secondName = resultSet.getString("surname");
                int salary = resultSet.getInt("salary");


                String projectName = resultSet.getString("project_name");
                Project project = new Project();
                project.setProjectName(projectName);
                developer.setId(idRes);
                developer.setName(name);
                developer.setSurname(secondName);
                developer.setSalary(salary);

                String skills = resultSet.getString("skills");
                String[] split = skills.split(",");

                for (String s : split) {
                    Skill skill = new Skill();
                    skill.setSkill(s);
                    developer.getSkills().add(skill);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    public void delete(int id) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM developers WHERE idDev=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Developer> getAll() {

        List<Developer> developers = new ArrayList<>();

        try (Connection connection = getConnection()) {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT idDev,name,surname,salary,projects.project_name, GROUP_CONCAT(skills.skills SEPARATOR ', ') AS skills" +
                    " FROM developers LEFT JOIN developers_has_skills ON developers.idDev = developers_has_skills.Developers_id LEFT JOIN skills ON developers_has_skills.Skills_id = skills.idSkill LEFT JOIN projects ON developers.Projects_id = projects.idProject GROUP BY idDev ");

            while (resultSet.next()) {

                int id = resultSet.getInt("idDev");
                String name = resultSet.getString("name");
                String secondName = resultSet.getString("surname");
                int salary = resultSet.getInt("salary");
                String nameProj = resultSet.getString("project_name");
                String skills = resultSet.getString("skills");

                Developer developer = new Developer();
                Project project = new Project();
                project.setProjectName(nameProj);
                developer.setProject(project);

                developer.setId(id);
                developer.setName(name);
                developer.setSurname(secondName);
                developer.setSalary(salary);
                developer.getProject().setProjectName(nameProj);


                if (skills == null) developer.setSkills(null);
                else {
                    String[] splitSkills = skills.split(",");
                    for (String skill : splitSkills) {
                        Skill skill1 = new Skill();
                        skill1.setSkill(skill);
                        developer.getSkills().add(skill1);
                    }
                }

                developers.add(developer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developers;
    }


}

