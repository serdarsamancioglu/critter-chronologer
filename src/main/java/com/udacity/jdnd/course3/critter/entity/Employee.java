package com.udacity.jdnd.course3.critter.entity;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.hibernate.annotations.Nationalized;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Skill> skills;

    @ManyToMany
    private List<Schedule> schedules;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
    private List<AvailableDay> days;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<AvailableDay> getDays() {
        return days;
    }

    public void setDays(Set<DayOfWeek> daySet) {
        List<AvailableDay> days = null;
        if (daySet != null) {
            days = new ArrayList<>();
            for (DayOfWeek d : daySet) {
                days.add(new AvailableDay(d, this));
            }
        }
        this.days = days;
    }

    public void addSkill(EmployeeSkill empSkill) {
        if (this.skills == null) {
            this.skills = new ArrayList<>();
        }
        this.skills.add(new Skill(this, empSkill));
    }

    public static Employee fromEmployeeDTO(EmployeeDTO eDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(eDTO, employee);
        employee.setDays(eDTO.getDaysAvailable());
        if (eDTO.getSkills() != null) {
            List<Skill> skills = new ArrayList<>();
            for (EmployeeSkill eSkill : eDTO.getSkills()) {
                skills.add(new Skill(employee, eSkill));
            }
            employee.setSkills(skills);
        }
        return employee;
    }

    public EmployeeDTO toEmployeeDTO() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName(this.name);
        employeeDTO.setId(this.id);
        List<EmployeeSkill> empSkills = new ArrayList<>();
        if (this.skills != null) {
            for (Skill s : this.skills) {
                empSkills.add(s.getEmpSkill());
            }
            employeeDTO.setSkills(new HashSet<>(empSkills));
        }
        if (this.days != null) {
            List<DayOfWeek> dayOfWeekList = new ArrayList<>();
            for (AvailableDay a: days) {
                dayOfWeekList.add(a.getDay());
            }
            employeeDTO.setDaysAvailable(Sets.newHashSet(dayOfWeekList));
        }

        return employeeDTO;
    }
}
