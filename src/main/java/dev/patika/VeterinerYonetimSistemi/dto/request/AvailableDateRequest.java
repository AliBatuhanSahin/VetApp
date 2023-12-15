package dev.patika.VeterinerYonetimSistemi.dto.request;

import dev.patika.VeterinerYonetimSistemi.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailableDateRequest {
    private LocalDate availableDate;
    private Doctor doctor;
}
