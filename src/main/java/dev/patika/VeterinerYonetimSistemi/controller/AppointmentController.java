package dev.patika.VeterinerYonetimSistemi.controller;

import dev.patika.VeterinerYonetimSistemi.dto.request.AppointmentRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.AppointmentResponse;
import dev.patika.VeterinerYonetimSistemi.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> findAll() {
        return appointmentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping("/createNewAppointment")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        return appointmentService.createAppointment(appointmentRequest);
    }


    @GetMapping("/filter/byDoctorId")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByDoctorAndDateRange(
            @RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<AppointmentResponse> appointments = appointmentService.getAppointmentsByDoctorAndDateRange(doctorId, startDate, endDate);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/filter/byAnimalId")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByAnimalAndDateRange(
            @RequestParam Long animalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<AppointmentResponse> appointments = appointmentService.getAppointmentsByAnimalAndDateRange(animalId, startDate, endDate);
        return ResponseEntity.ok(appointments);
    }


    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse appointmentUpdate(@PathVariable Long id, @RequestBody AppointmentRequest request) {
        return appointmentService.appointmentUpdate(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void appointmentDelete(@PathVariable("id") Long id) {
        appointmentService.appointmentDeleteById(id);
    }
}