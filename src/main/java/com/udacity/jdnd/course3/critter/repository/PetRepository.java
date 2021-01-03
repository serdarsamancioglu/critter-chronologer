package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PetRepository {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    private final String GET_ALL = "Select p from Pet p";

    private final String PETS_BY_OWNER = "Select p from Pet p where p.owner.id = :ownerId";

    public Pet find(Long id) {
        return entityManager.find(Pet.class, id);
    }

    public void persist(Pet pet) {
        entityManager.persist(pet);
    }

    public Pet merge(Pet pet) {
        return entityManager.merge(pet);
    }

    public List<Pet> getAllPets() {
        TypedQuery<Pet> query = entityManager.createQuery(GET_ALL, Pet.class);
        return query.getResultList();
    }

    public List<Pet> getPetsByOwner(long ownerId) {
        TypedQuery<Pet> query = entityManager.createQuery(PETS_BY_OWNER, Pet.class);
        query.setParameter("ownerId", ownerId);
        return query.getResultList();
    }
}
