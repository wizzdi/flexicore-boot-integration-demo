package com.example.pet.events;

import com.example.pet.model.Pet;

public class PetCreated {
    private final Pet pet;

    public PetCreated(Pet pet) {
        this.pet = pet;
    }


    public Pet getPet() {
        return pet;
    }
}
