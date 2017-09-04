package com.sharyi_dmytro.practice.module02.Entities;

import javax.persistence.*;
import java.util.List;


@Entity
@NamedQuery(name = "Developer.getAll", query = "SELECT developer FROM Developer developer")
public class Developer {
    private int id;
    private String name;
    private String secondName;
    private int salary;
    private Project project;
    private List<Skill> skills;

    public Developer() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDeveloper", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", length = 45, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "secondName", length = 45, nullable = false)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Column(name = "salary", nullable = false)
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @ManyToOne()
    @JoinColumn(name = "ProjectId", referencedColumnName = "idProject")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Developer developer = (Developer) o;

        if (id != developer.id) return false;
        if (salary != developer.salary) return false;
        if (name != null ? !name.equals(developer.name) : developer.name != null) return false;
        if (secondName != null ? !secondName.equals(developer.secondName) : developer.secondName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + salary;
        return result;
    }

    @Override
    public String toString() {
        String sk = "";
        if (skills != null) {
            for (int i = 0; i < skills.size(); i++) {

                sk += skills.get(i).getSkill() + (i == skills.size() - 1 ? "" : ",");
            }
        } else {
            sk = "Developer has 0 skills";
        }

        return "Developer " +
                "id=" + id +
                ",| name: '" + name + '\'' +
                ",| secondName: '" + secondName + '\'' +
                ",| salary: " + salary +
                ",| skills: " + sk +
                " | project: " + (project == null ? "Dont have a project!" : project.getName());
    }
}



