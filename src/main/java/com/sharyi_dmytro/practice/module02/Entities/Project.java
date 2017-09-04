package com.sharyi_dmytro.practice.module02.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project", schema = "db_for_hibernate")
@NamedQuery(name = "Project.getAll", query = "SELECT project FROM Project project")
public class Project {
    private int id;
    private String name;
    private int cost;
    private Company company;
    private Customer customer;
    private List<Developer> developers;

    public Project() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProject", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int idProject) {
        this.id = idProject;
    }

    @Column(name = "name", length = 45, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "cost", nullable = false)
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != project.id) return false;
        if (cost != project.cost) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + cost;
        return result;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idCompany", referencedColumnName = "idCompany")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company companyByIdCompany) {
        this.company = companyByIdCompany;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idCustomer", referencedColumnName = "idCustomer")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customerByIdCustomer) {
        this.customer = customerByIdCustomer;
    }


    @Override
    public String toString() {
        String dev = "";
        if (developers != null) {
            for (int i = 0; i < developers.size(); i++) {
                dev += developers.get(i).getName() + " " + developers.get(i).getSecondName() + (i == developers.size() - 1 ? "" : ", ");
            }
        } else dev = "Project has 0 developers";

        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", developers=" + developers.size() + "  " + String.valueOf(dev) +
                ", customer=" + (customer.getProjects().stream().noneMatch(project -> project.equals(this)) ? "Dont have customer" : customer.getName() + " " ) +
                ", company=" + (company.getProjects().stream().noneMatch(project -> project.equals(this)) ? "Dont have a company" : company.getName()) +
                '}';
    }
}