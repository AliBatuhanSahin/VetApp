package dev.patika.VeterinerYonetimSistemi.dto.response;

import dev.patika.VeterinerYonetimSistemi.entity.Customer;
import dev.patika.VeterinerYonetimSistemi.entity.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnimalResponse {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    private Customer customer;

}
