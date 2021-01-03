package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ScheduleRepository {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    private final String SELECT_ALL = "Select s from Schedule s";

    private final String SELECT_FOR_PET =
            "Select s from Schedule s join s.pets p WHERE p.id=:petId";

    private final String SELECT_FOR_EMPLOYEE = "Select s from Schedule s " +
            "join s.employees e where e.id=:employeeId";

    private final String SELECT_FOR_CUSTOMER =
            "Select s from Schedule s join s.pets p WHERE p.owner.id=:customerId";

    public Schedule find(Long id) {
        return entityManager.find(Schedule.class, id);
    }

    public void persist(Schedule schedule) {
        entityManager.persist(schedule);
    }

    public Schedule merge(Schedule schedule) {
        return entityManager.merge(schedule);
    }

    public List<Schedule> findAll() {
        TypedQuery<Schedule> query = entityManager.createQuery(SELECT_ALL, Schedule.class);
        return query.getResultList();
    }

    public List<Schedule> getSchedulesForPet(Long petId) {
        TypedQuery<Schedule> query = entityManager.createQuery(SELECT_FOR_PET, Schedule.class);
        query.setParameter("petId", petId);
        return query.getResultList();
    }

    public List<Schedule> getSchedulesForEmployee(Long employeeId) {
        TypedQuery<Schedule> query = entityManager.createQuery(SELECT_FOR_EMPLOYEE, Schedule.class);
        query.setParameter("employeeId", employeeId);
        return query.getResultList();
    }

    public List<Schedule> getSchedulesForCustomer(Long customerId) {
        TypedQuery<Schedule> query = entityManager.createQuery(SELECT_FOR_CUSTOMER, Schedule.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
}
