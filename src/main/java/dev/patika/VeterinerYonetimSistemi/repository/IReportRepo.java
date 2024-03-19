package dev.patika.VeterinerYonetimSistemi.repository;

import dev.patika.VeterinerYonetimSistemi.entity.Animal;
import dev.patika.VeterinerYonetimSistemi.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReportRepo extends JpaRepository<Report,Long> {
    Optional<Report> findByTitle(String title);
}
