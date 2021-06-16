package com.example.pet.interfaces;

import com.example.pet.model.Pet;
import com.example.pet.response.PetGroomResult;

public interface PetGroomer {

    PetGroomResult groom(Pet pet);

}
