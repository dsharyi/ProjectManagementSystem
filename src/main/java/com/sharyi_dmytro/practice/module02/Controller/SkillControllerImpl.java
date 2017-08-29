package com.sharyi_dmytro.practice.module02.Controller;

import com.sharyi_dmytro.practice.module02.DAO.SkillDAO;
import com.sharyi_dmytro.practice.module02.Entities.Skill;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

/**
 * Created by nonal on 31.07.2017.
 */
public class SkillControllerImpl implements SkillController {

    private SkillDAO daoSkill;

    public SkillControllerImpl(SkillDAO daoSkill) {
        this.daoSkill = daoSkill;
    }

    public boolean create(String nameSkill) {
        daoSkill.create(nameSkill);
        return true;
    }

    public Skill read(int id) throws WrongId {
        if (daoSkill.getAll().stream().map(Skill::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Навыка с таким ID не существует, повторите ввод!");
        return daoSkill.read(id);
    }

    public boolean update(int id, String newName) throws WrongId {
        if (daoSkill.getAll().stream().map(Skill::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Навыка с таким ID не существует, повторите ввод!");
        daoSkill.update(id, newName);
        return true;
    }

    public boolean delete(int id) throws WrongId {
        if (daoSkill.getAll().stream().map(Skill::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Навыка с таким ID не существует, повторите ввод!");
        daoSkill.delete(id);
        return true;
    }

    public void showAllSkills() {
        daoSkill.getAll().forEach(System.out::println);
    }
}