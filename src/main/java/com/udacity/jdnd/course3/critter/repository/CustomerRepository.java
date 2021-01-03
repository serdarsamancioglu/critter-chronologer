package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerRepository {

    private String SELECT_ALL = "Select c from Customer c";

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    public Customer find(Long id) {
        return entityManager.find(Customer.class, id);
    }

    public void persist(Customer customer) {
        entityManager.persist(customer);
    }

    public Customer merge(Customer customer) {
        return entityManager.merge(customer);
    }

    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery(SELECT_ALL, Customer.class);
        return query.getResultList();
    }

}
