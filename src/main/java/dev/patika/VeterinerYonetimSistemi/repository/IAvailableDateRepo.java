package dev.patika.VeterinerYonetimSistemi.repository;
import dev.patika.VeterinerYonetimSistemi.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAvailableDateRepo extends JpaRepository<AvailableDate,Long> {
    Optional<AvailableDate> findByAvailableDate(LocalDate availableDate);
    List<AvailableDate> findByDoctorId(Long doctorId);
    Optional<AvailableDate> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);

}
