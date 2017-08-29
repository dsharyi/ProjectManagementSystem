package com.sharyi_dmytro.practice.module02.DAO;

import com.sharyi_dmytro.practice.module02.Entities.Skill;

import java.util.List;


public interface SkillDAO {
    void create(String nameSkill);

    Skill read(int id);

    void update(int id, String newName);

    void delete(int id);

    List<Skill> getAll();

}

