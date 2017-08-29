package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.Entities.Skill;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

/**
 * Created by nonal on 31.07.2017.
 */
public interface SkillController {

    boolean create(String nameSkill);

    Skill read(int id) throws WrongId;

    boolean update(int id, String newName) throws WrongId;

    boolean delete(int id) throws WrongId;

    void showAllSkills();
}

