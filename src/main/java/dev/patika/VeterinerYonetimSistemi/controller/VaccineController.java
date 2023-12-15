package dev.patika.VeterinerYonetimSistemi.controller;

import dev.patika.VeterinerYonetimSistemi.dto.request.VaccineRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.AnimalResponse;
import dev.patika.VeterinerYonetimSistemi.dto.response.VaccineResponse;
import dev.patika.VeterinerYonetimSistemi.entity.Vaccine;
import dev.patika.VeterinerYonetimSistemi.service.VaccineService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vaccines")
@AllArgsConstructor
public class VaccineController {
    private final VaccineService vaccineService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VaccineResponse> findAll() {
        return vaccineService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse getVaccineById(@PathVariable Long id) {
        return vaccineService.getVaccineById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public VaccineResponse createVaccine(@RequestBody VaccineRequest vaccineRequest) {
        return vaccineService.createVaccine(vaccineRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse VaccineUpdate(@PathVariable Long id, @RequestBody VaccineRequest request) {
        return vaccineService.vaccineUpdate(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void VaccineDelete(@PathVariable("id") Long id) {
        vaccineService.vaccineDeleteById(id);
    }

    @GetMapping("/{animalId}/vaccines")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VaccineResponse>> getVaccinesByAnimalId(@PathVariable Long animalId) {
        List<VaccineResponse> vaccines = vaccineService.getVaccinesByAnimalId(animalId);
        return ResponseEntity.ok(vaccines);
    }

    @GetMapping("/upcomingVaccines")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AnimalResponse>> getAnimalsWithUpcomingVaccines(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<AnimalResponse> animals = vaccineService.getAnimalsWithUpcomingVaccines(startDate, endDate);
        return ResponseEntity.ok(animals);
    }
}

