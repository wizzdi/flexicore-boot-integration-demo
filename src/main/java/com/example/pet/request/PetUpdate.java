package com.example.pet.request;

public class PetUpdate extends PetCreate{
    private String id;


    public String getId() {
        return id;
    }

    public <T extends PetUpdate> T setId(String id) {
        this.id = id;
        return (T) this;
    }
}
