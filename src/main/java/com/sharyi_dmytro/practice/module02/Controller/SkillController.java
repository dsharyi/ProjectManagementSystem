package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.Entities.Skill;
import com.sharyi_dmytro.practice.module02.Exceptions.SkillNull;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.util.List;

/**
 * Created by nonal on 31.07.2017.
 */
public interface SkillController {

    boolean create(String nameSkill) throws SkillNull;

    Skill read(int id) throws WrongId;

    boolean update(int id, String newName) throws WrongId;

    boolean delete(int id) throws WrongId;

    List<Skill> showAllSkills();
}

