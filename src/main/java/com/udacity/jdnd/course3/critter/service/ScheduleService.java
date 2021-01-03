package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.entity.Skill;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    public Schedule createSchedule(Schedule schedule, List<Long> employeeIds,
                                   List<Long> petIds, Set<EmployeeSkill> activities) {
        List<Employee> employees = new ArrayList<>();
        for (Long empId: employeeIds) {
            employees.add(employeeRepository.find(empId));
        }
        List<Pet> pets = new ArrayList<>();
        for (Long petId: petIds) {
            pets.add(petRepository.find(petId));
        }
        List<Skill> skills = new ArrayList<>();
        for (EmployeeSkill es : activities) {
            skills.add(new Skill(schedule, es));
        }
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        schedule.setActivities(skills);
        scheduleRepository.persist(schedule);
        return schedule;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesForPet(Long petId) {
        return scheduleRepository.getSchedulesForPet(petId);
    }

    public List<Schedule> getSchedulesForEmployee(Long employeeId) {
        return scheduleRepository.getSchedulesForEmployee(employeeId);
    }

    public List<Schedule> getSchedulesForCustomer(Long customerId) {
        return scheduleRepository.getSchedulesForCustomer(customerId);
    }
}
