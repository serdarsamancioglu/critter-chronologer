package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public Pet savePet(Pet pet) {
        if (pet.getId() == null || pet.getId() <= 0) {
            pet.setId(null);
            petRepository.persist(pet);
        }
        else {
            pet = petRepository.merge(pet);
        }
        return pet;
    }

    public Pet getPet(long id) {
        return petRepository.find(id);
    }

    public List<Pet> getAllPets() {
        return petRepository.getAllPets();
    }

    public List<Pet> getPetsByOwner(long ownerId) {
        return petRepository.getPetsByOwner(ownerId);
    }
}
