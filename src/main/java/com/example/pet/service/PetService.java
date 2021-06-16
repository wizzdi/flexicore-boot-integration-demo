package com.example.pet.service;

import com.example.pet.data.PetRepository;
import com.example.pet.events.PetCreated;
import com.example.pet.interfaces.PetGroomer;
import com.example.pet.model.Pet;
import com.example.pet.request.PetCreate;
import com.example.pet.request.PetUpdate;
import com.example.pet.response.PetGroomResponse;
import com.example.pet.response.PetGroomResult;
import com.wizzdi.flexicore.boot.base.init.FlexiCorePluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    @Lazy
    public FlexiCorePluginManager flexiCorePluginManager;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public Pet createPet(PetCreate petCreate) {
        Pet pet = new Pet();
        pet.setId(UUID.randomUUID().toString());
        pet.setName(petCreate.getName());
        petRepository.save(pet);
        applicationEventPublisher.publishEvent(new PetCreated(pet));
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

    public PetGroomResponse groomPet(String id) {
        Pet pet=petRepository.getOne(id);
        List<PetGroomResult> results = flexiCorePluginManager.getPluginApplicationContexts().stream()
                .map(f -> f.getBeansOfType(PetGroomer.class).values())
                .flatMap(Collection::stream)
                .map(f -> f.groom(pet)).collect(Collectors.toList());
        return new PetGroomResponse(results);
    }
}
