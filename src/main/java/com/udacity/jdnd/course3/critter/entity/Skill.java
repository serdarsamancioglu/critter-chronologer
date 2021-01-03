package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Schedule schedule;

    private EmployeeSkill empSkill;

    public Skill() {
    }

    public Skill(Employee employee, EmployeeSkill empSkill) {
        this.employee = employee;
        this.empSkill = empSkill;
    }

    public Skill(Schedule schedule, EmployeeSkill empSkill) {
        this.schedule = schedule;
        this.empSkill = empSkill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeSkill getEmpSkill() {
        return empSkill;
    }

    public void setEmpSkill(EmployeeSkill empSkill) {
        this.empSkill = empSkill;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
