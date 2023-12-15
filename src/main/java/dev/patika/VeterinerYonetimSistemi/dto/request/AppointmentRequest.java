package dev.patika.VeterinerYonetimSistemi.dto.request;

import dev.patika.VeterinerYonetimSistemi.entity.Animal;
import dev.patika.VeterinerYonetimSistemi.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentRequest {
    private LocalDateTime appointmentDate;
    private Animal animal;
    private Doctor doctor;
}
