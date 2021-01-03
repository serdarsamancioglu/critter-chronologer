package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.AvailableDay;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class AvailableDaysRepository {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    private String FIND_BY_EMPLOYEE = "Select a from AvailableDay a where a.employee_id=:eId";

    private String FIND_FOR_SERVICE = "Select d FROM AvailableDay d" +
            " where d.day = :dayOfWeek";

    public AvailableDay find(Long id) {
        return entityManager.find(AvailableDay.class, id);
    }

    public void persist(AvailableDay day) {
        entityManager.persist(day);
    }

    public AvailableDay merge(AvailableDay day) {
        return entityManager.merge(day);
    }

    public List<AvailableDay> findByEmployee(long employeeId) {
        TypedQuery<AvailableDay> query = entityManager.createQuery(FIND_BY_EMPLOYEE, AvailableDay.class);
        query.setParameter("eId", employeeId);
        return query.getResultList();
    }

    public List<AvailableDay> getAvailableDaysForService(LocalDate date) {
        TypedQuery<AvailableDay> query = entityManager.createQuery(FIND_FOR_SERVICE, AvailableDay.class);
        query.setParameter("dayOfWeek", date.getDayOfWeek());
        return query.getResultList();
    }
}
