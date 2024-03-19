package dev.patika.VeterinerYonetimSistemi.dto.response;

import dev.patika.VeterinerYonetimSistemi.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportResponse {
    private Long id;
    private String title;
    private String diagnosis;
    private Double price;
    private Appointment appointment;
}