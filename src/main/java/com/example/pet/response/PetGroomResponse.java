package com.example.pet.response;

import java.util.List;

public class PetGroomResponse {
    private final List<PetGroomResult> petGroomResult;

    public PetGroomResponse(List<PetGroomResult> petGroomResult) {
        this.petGroomResult = petGroomResult;
    }

    public List<PetGroomResult> getPetGroomResult() {
        return petGroomResult;
    }
}
