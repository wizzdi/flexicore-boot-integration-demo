package com.example.pet.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pet {
    @Id
    private String id;
    private String name;


    @Id
    public String getId() {
        return id;
    }

    public <T extends Pet> T setId(String id) {
        this.id = id;
        return (T) this;
    }

    public String getName() {
        return name;
    }

    public <T extends Pet> T setName(String name) {
        this.name = name;
        return (T) this;
    }
}
