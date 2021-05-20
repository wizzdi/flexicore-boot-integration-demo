package com.example.pet.data;

import com.example.pet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {

  List<Pet> findByName(String lastName);

  Optional<Pet> findById(String id);
}