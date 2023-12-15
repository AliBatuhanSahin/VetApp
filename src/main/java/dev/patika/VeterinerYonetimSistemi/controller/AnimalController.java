package dev.patika.VeterinerYonetimSistemi.controller;

import dev.patika.VeterinerYonetimSistemi.dto.request.AnimalRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.AnimalResponse;
import dev.patika.VeterinerYonetimSistemi.dto.response.CustomerResponse;
import dev.patika.VeterinerYonetimSistemi.entity.Animal;
import dev.patika.VeterinerYonetimSistemi.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animals")
@AllArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> findAll() {
        return animalService.findAll();
    }


    @GetMapping("/findByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse findByName(@PathVariable String name) {
        return animalService.findByName(name);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalResponse createAnimal(@RequestBody AnimalRequest animalRequest) {
        return animalService.createAnimal(animalRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse animalUpdate(@PathVariable Long id, @RequestBody AnimalRequest request) {
        return animalService.animalUpdate(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void animalDelete(@PathVariable("id") Long id) {
        animalService.animalDeleteById(id);
    }
}
