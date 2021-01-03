package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class SkillRepository {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    public Pet find(Long id) {
        return entityManager.find(Pet.class, id);
    }

    public void persist(Skill skill) {
        entityManager.persist(skill);
    }

    public Skill merge(Skill skill) {
        return entityManager.merge(skill);
    }
}
