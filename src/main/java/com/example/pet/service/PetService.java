package com.example.pet.service;

import com.example.pet.data.PetRepository;
import com.example.pet.model.Pet;
import com.example.pet.request.PetCreate;
import com.example.pet.request.PetUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet createPet(PetCreate petCreate) {
        Pet pet = new Pet();
        pet.setId(UUID.randomUUID().toString());
        pet.setName(petCreate.getName());
        petRepository.save(pet);
        return pet;
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Pet updatePet(PetUpdate petUpdate) {
        Pet pet = petRepository.findById(petUpdate.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"no pet with id "+petUpdate.getId()));
        pet.setName(petUpdate.getName());
        petRepository.save(pet);
        return pet;
    }


    public List<Pet> findByName(String lastName) {
        return petRepository.findByName(lastName);
    }

    public Optional<Pet> findById(String id) {
        return petRepository.findById(id);
    }

    public void deleteById(String s) {
        petRepository.deleteById(s);
    }
}
