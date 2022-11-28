package com.learn.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Setter
// @Getter
// @NoArgsConstructor
// @AllArgsConstructor

@Entity
public class Employee {
    @Id
    private int empid;
    @Column
    private String name;
    @Column
    private String email;
    @ManyToOne
    private Team team;
    public int getEmpid() {
        return empid;
    }
    public void setEmpid(int empid) {
        this.empid = empid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Team getTeam() {
        return team;
    }
    public Employee(int empid, String name, String email, Team team) {
        this.empid = empid;
        this.name = name;
        this.email = email;
        this.team = team;
    }
    
    public Employee() {
    }
    public void setTeam(Team team) {
        this.team = team;
    }
    
}
