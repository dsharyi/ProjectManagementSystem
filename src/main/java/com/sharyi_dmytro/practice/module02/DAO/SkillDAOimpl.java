package com.sharyi_dmytro.practice.module02.DAO;

import com.sharyi_dmytro.practice.module02.DB.PoolConnections;
import com.sharyi_dmytro.practice.module02.Entities.Skill;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SkillDAOimpl extends PoolConnections implements SkillDAO {
    public SkillDAOimpl(DataSource dataSource) {
        super(dataSource);
    }

    public void create(String nameSkill) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO skills(skills) VALUE (?)");
            preparedStatement.setString(1, nameSkill);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Skill read(int id) {
        Skill skill = new Skill();
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM skills WHERE idSkill = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idSkill = resultSet.getInt("idSkill");
                String nameSkill = resultSet.getString("skills");

                skill.setId(idSkill);
                skill.setSkill(nameSkill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skill;
    }

    public void update(int id, String newName) {
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE skills SET skills = ? WHERE idSkill = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM developers_has_skills WHERE Skills_id = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM skills WHERE idSkill = ?;")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Skill> getAll() {
        List<Skill> skills = new ArrayList<>();
        try (Connection connection = getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM skills");

            while (resultSet.next()) {
                int idSkill = resultSet.getInt("idSkill");
                String skill1 = resultSet.getString("skills");

                Skill skill = new Skill();
                skill.setId(idSkill);
                skill.setSkill(skill1);

                skills.add(skill);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skills;
    }
}

