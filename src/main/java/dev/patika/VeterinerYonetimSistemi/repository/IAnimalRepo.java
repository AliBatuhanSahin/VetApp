package dev.patika.VeterinerYonetimSistemi.repository;

import dev.patika.VeterinerYonetimSistemi.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAnimalRepo extends JpaRepository<Animal,Long> {
    Optional<Animal> findByName(String name);
    List<Animal> findAllByCustomerId(Long customerId);

}
