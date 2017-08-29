package com.sharyi_dmytro.practice.module02.Entities;

import java.util.List;

public class Customer {
    private int id;
    private String customerName;
    private List<Project> projects;

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return customerName;
    }

    public void setName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Customer " +
                "id=" + id +
                ",| name: '" + customerName;

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Customer(int id, String customerName, List<Project> projects) {
        this.id = id;
        this.customerName = customerName;
        this.projects = projects;
    }
}
