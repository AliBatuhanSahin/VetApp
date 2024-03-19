package dev.patika.VeterinerYonetimSistemi.repository;
import dev.patika.VeterinerYonetimSistemi.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IVaccineRepo extends JpaRepository<Vaccine,Long> {
    Optional<Vaccine> findByNameAndCode(String name, String code);
    List<Vaccine> findVaccineByName(String name);

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);

    List<Vaccine> findByAnimalName(String animalName);
    @Query("SELECT v FROM Vaccine v WHERE v.protectionFinishDate BETWEEN :startDate AND :endDate")
    List<Vaccine> findAnimalsWithUpcomingVaccines(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
