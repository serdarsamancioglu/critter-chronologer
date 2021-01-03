package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.repository.AvailableDaysRepository;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    AvailableDaysRepository dayRepository;

    public Employee saveEmployee(Employee employee) {
        if (employee.getId() == null || employee.getId() <= 0) {
            employee.setId(null);
            employeeRepository.persist(employee);
        }
        else {
            employee = employeeRepository.merge(employee);
        }
        return employee;
    }

    public Employee getEmployee(long employeeId) {
        return employeeRepository.find(employeeId);
    }

    public List<Employee> findEmployeeForService(Set<EmployeeSkill> skillSet, LocalDate date) {
        List<AvailableDay> days = dayRepository.getAvailableDaysForService(date);
        List<Employee> employees = new ArrayList<>();
        for (AvailableDay d: days) {
            employees.add(d.getEmployee());
        }
        List<Employee> skilledEmployees = new ArrayList<>();
        for (Employee e: employees) {
            List<EmployeeSkill> empSkills = new ArrayList<>();
            for (Skill s: e.getSkills()) {
                empSkills.add(s.getEmpSkill());
            }
            if (empSkills.containsAll(skillSet)) {
                skilledEmployees.add(e);
            }
        }
        return skilledEmployees;
    }

    public Customer saveCustomer(Customer customer) {
        if (customer.getId() == null || customer.getId() <= 0) {
            customer.setId(null);
            customerRepository.persist(customer);
        }
        else {
            customer = customerRepository.merge(customer);
        }
        return customer;
    }

    public Customer getCustomer(long id) {
        return customerRepository.find(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getOwnerByPet(long petId) {
        Pet pet = petRepository.find(petId);
        return pet.getOwner();
    }
}
