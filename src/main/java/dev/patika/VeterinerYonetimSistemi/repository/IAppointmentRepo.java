package dev.patika.VeterinerYonetimSistemi.repository;
import dev.patika.VeterinerYonetimSistemi.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAppointmentRepo extends JpaRepository<Appointment,Long> {
    Optional<Appointment> findByAppointmentDateAndAnimalIdAndDoctorId(LocalDateTime appointmentDate, Long animal_id, Long doctor_id);
    List<Appointment> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);
    List<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime startDate, LocalDateTime endDate);

}
