package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class EmployeeRepository {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    public Employee find(Long id) {
        return entityManager.find(Employee.class, id);
    }

    public void persist(Employee employee) {
        entityManager.persist(employee);
    }

    public Employee merge(Employee employee) {
        return entityManager.merge(employee);
    }

}
