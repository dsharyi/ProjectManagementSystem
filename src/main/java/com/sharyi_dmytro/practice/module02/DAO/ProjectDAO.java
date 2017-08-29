package com.sharyi_dmytro.practice.module02.DAO;

import com.sharyi_dmytro.practice.module02.Entities.Project;

import java.util.List;

/**
 * Created by nonal on 13.07.2017.
 */
public interface ProjectDAO {
    void create(String nameProj, int cost, int idCompany, int idCustomer);

    Project read(int id);

    void update(int id, String newName, int cost);

    void delete(int id);

    List<Project> getAll();

}