package com.sharyi_dmytro.practice.module02.DAO;

import com.sharyi_dmytro.practice.module02.Entities.Company;
import com.sharyi_dmytro.practice.module02.Entities.Project;

import java.util.List;

/**
 * Created by nonal on 13.07.2017.
 */
public interface CompanyDAO {
        void create(String name);

        Company read(int id);

        void update(int id, String newName);

        void delete(int id);

        List<Company> getAll();

    }
