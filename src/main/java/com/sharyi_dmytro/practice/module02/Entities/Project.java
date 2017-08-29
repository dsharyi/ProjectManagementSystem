package com.sharyi_dmytro.practice.module02.Entities;

import java.util.List;

public class Project {
    private int id;
    private String projectName;
    private int projectCost;
    private Company company;
    private Customer customer;
    private List<Developer>developers;

    public Project(String projectName, int projectCost, Customer customer, Company company) {

    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public Project(String projectName, int projectCost, Company company, Customer customer) {
        this.projectName = projectName;
        this.projectCost = projectCost;
        this.company = company;
        this.customer = customer;


    }

    public Project() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(int projectCost) {
        this.projectCost = projectCost;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
    return "Developer " +
            "id=" + id +
            ",| project: '" + projectName + '\'' +
            ",| cost: " + projectCost +
            ",| company: " + company +
            ",| customer: " + customer +
            " | developers: " + (developers == null ? "Dont have developers!" : developers);
}
}