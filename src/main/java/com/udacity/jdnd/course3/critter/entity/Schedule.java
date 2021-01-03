package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Employee> employees;

    @ManyToMany
    private List<Pet> pets;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Skill> activities;

    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Skill> getActivities() {
        return activities;
    }

    public void setActivities(List<Skill> activities) {
        this.activities = activities;
    }

    public static Schedule fromScheduleDTO(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }

    public ScheduleDTO toScheduleDTO() {
        ScheduleDTO dto = new ScheduleDTO();
        BeanUtils.copyProperties(this, dto);
        dto.setEmployeeIds(this.getEmployees().stream()
                .map(Employee::getId).collect(Collectors.toList()));
        dto.setPetIds(this.getPets().stream()
                .map(Pet::getId).collect(Collectors.toList()));
        dto.setActivities(this.getActivities().stream()
                .map(Skill::getEmpSkill).collect(Collectors.toSet()));
        return dto;
    }

}
