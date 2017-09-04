package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.DAO.MainDAO;
import com.sharyi_dmytro.practice.module02.Entities.Skill;
import com.sharyi_dmytro.practice.module02.Exceptions.SkillNull;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.util.List;

/**
 * Created by nonal on 31.07.2017.
 */
public class SkillControllerImpl implements SkillController {

    MainDAO<Skill, Integer> skillDao;

    public SkillControllerImpl(MainDAO<Skill, Integer> skillDao) {
        this.skillDao = skillDao;
    }

    public boolean create(String nameSkill) throws SkillNull {
        if (skillDao.getAll().stream().anyMatch(skill -> skill.getSkill().equals(nameSkill)))
            throw new SkillNull("Такой навык уже существует, повторите свой ввод");
        Skill skill = new Skill();
        skill.setSkill(nameSkill);

        skillDao.create(skill);
        return true;
    }

    public Skill read(int id) throws WrongId {
        if (skillDao.getAll().stream().noneMatch(skill -> skill.getId() == id))
            throw new WrongId("Навыка с таким ID не существует, повторите свой ввод");

        return skillDao.read(id);
    }

    @Override
    public boolean update(int id, String newName) throws WrongId {
        if (skillDao.getAll().stream().noneMatch(skill -> skill.getId() == id))
            throw new WrongId("Навыка с таким айди не существует, повторите свой ввод");
        Skill skill = read(id);
        skill.setSkill(newName);

        skillDao.update(skill);
        return false;
    }

    @Override
    public boolean delete(int id) throws WrongId {
        if (skillDao.getAll().stream().noneMatch(skill -> skill.getId() == id))
            throw new WrongId("Навыка с таким айди не существует");

        Skill skill = read(id);

        skillDao.delete(skill);

        return true;
    }

    @Override
    public List<Skill> showAllSkills() {
        return skillDao.getAll();
    }
}