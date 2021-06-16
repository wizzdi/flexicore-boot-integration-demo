package com.example.pet.controller;

import com.example.pet.model.Pet;
import com.example.pet.request.PetCreate;
import com.example.pet.request.PetUpdate;
import com.example.pet.response.PetGroomResponse;
import com.example.pet.service.PetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/pet")
@Tag(name = "Pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public Pet updatePet(@RequestBody PetCreate petCreate){
        return petService.createPet(petCreate);
    }

    @PutMapping
    public Pet updatePet(@RequestBody PetUpdate petUpdate){
        return petService.updatePet(petUpdate);
    }

    @GetMapping
    public List<Pet> getPets(){
        return petService.findAll();
    }

    @GetMapping("/{id}")
    public Pet getPet(@PathVariable("id")String id){
        return petService.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"not pet with id "+id));
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable("id")String id){
        petService.deleteById(id);
    }

    @PostMapping("/groom/{id}")
    public PetGroomResponse groomPet(@PathVariable("id")String id){
        return petService.groomPet(id);
    }
    @PostMapping("/groom")
    public Set<String> getGroomers() {
        return petService.getGroomers();
    }
}
