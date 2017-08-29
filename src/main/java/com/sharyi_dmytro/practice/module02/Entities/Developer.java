package com.sharyi_dmytro.practice.module02.Entities;

import java.util.ArrayList;
import java.util.List;

public class Developer {
    private int id;
    private String name;
    private String surname;
    private int salary;
    private Project project;
    private ArrayList<Skill> skills = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
            String sk = "";
            if (skills != null) {
                for (int i = 0; i < skills.size(); i++) {

                    sk += skills.get(i).getSkill() + (i == skills.size() - 1 ? "" : ",");
                }
            } else {
                sk = "Developer don't have a skills";
            }

            return "Developer " +
                    "id=" + id +
                    ",| name: '" + name + '\'' +
                    ",| secondName: '" + surname + '\'' +
                    ",| salary: " + salary +
                    ",| skills: " + sk +
                    " | project: " + (project == null ? "Dont have a project!" : project.getProjectName());
        }
}



