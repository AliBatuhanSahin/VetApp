package dev.patika.VeterinerYonetimSistemi.dto.response;

import dev.patika.VeterinerYonetimSistemi.entity.Doctor;
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
public class AvailableDateResponse {
    private LocalDate availableDate;
    private Long id;
    private List<DoctorResponse> doctors;
}
