package com.example.pet.model;

import com.flexicore.model.SecurityUser;

import javax.persistence.Entity;

@Entity
public class FakeUser extends SecurityUser {

    private String email;


    public String getEmail() {
        return email;
    }

    public <T extends FakeUser> T setEmail(String email) {
        this.email = email;
        return (T) this;
    }
}
