package com.sharyi_dmytro.practice.module02.Entities;

import java.util.List;

public class Company {
    private int id;
    private String name;
    private List<Project> projects;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

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

    @Override
    public String toString() {
        return "Company " +
                "id=" + id +
                ",| name: '" + name;

    }

    public Company(String name) {
        this.name = name;
    }

    public Company() {
    }

    public Company(int id, String name, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.projects = projects;
    }
}