package dev.patika.VeterinerYonetimSistemi.dto.request;

import dev.patika.VeterinerYonetimSistemi.entity.Customer;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnimalRequest {
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    private Customer customer;
}
