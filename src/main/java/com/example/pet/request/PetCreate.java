package com.example.pet.request;

public class PetCreate {
    private String name;

    public String getName() {
        return name;
    }

    public <T extends PetCreate> T setName(String name) {
        this.name = name;
        return (T) this;
    }
}
