package dev.patika.VeterinerYonetimSistemi.dto.request;

import dev.patika.VeterinerYonetimSistemi.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportRequest {
    private String title;
    private String diagnosis;
    private Double price;
    private Appointment appointment;
}
