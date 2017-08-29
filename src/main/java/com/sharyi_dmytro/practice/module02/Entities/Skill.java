package com.sharyi_dmytro.practice.module02.Entities;

/**
 * Created by nonal on 11.07.2017.
 */
public class Skill {
    private int id;
    private String skill;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Skill " +
                "id=" + id +
                ",| name: '" + skill;

    }

}

