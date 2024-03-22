package dev.patika.VeterinerYonetimSistemi.service;

import dev.patika.VeterinerYonetimSistemi.dto.request.AnimalRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.AnimalResponse;
import dev.patika.VeterinerYonetimSistemi.dto.response.CustomerResponse;
import dev.patika.VeterinerYonetimSistemi.entity.Animal;
import dev.patika.VeterinerYonetimSistemi.entity.Customer;
import dev.patika.VeterinerYonetimSistemi.mapper.AnimalMapper;
import dev.patika.VeterinerYonetimSistemi.repository.IAnimalRepo;
import dev.patika.VeterinerYonetimSistemi.repository.ICustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final IAnimalRepo animalRepo;
    private final AnimalMapper animalMapper;

    public List<AnimalResponse> findAll() {
        return animalMapper.asOutput(animalRepo.findAll());
    }

    //Değerlendirme Formu 16; Proje isterlerine göre hayvanın ismine göre filtrelendiği metod
    public AnimalResponse findByName(String name) {
        Optional<Animal> optionalAnimal = animalRepo.findByName(name);
        return animalMapper.asOutput(optionalAnimal.get());
    }

    //Değerlendirme Formu 18; Proje isterlerine göre hayvan sahibinin sistemde kayıtlı tüm hayvanlarını görüntüleyen metod
    public List<Animal> getAnimalsByCustomerId(String name) {
        return animalRepo.findAllByCustomerName(name);
    }

    public AnimalResponse getAnimalById(Long id){
        Optional<Animal> optionalAnimal = animalRepo.findById(id);
        if (optionalAnimal.isPresent()){
            return animalMapper.asOutput(optionalAnimal.get());
        }else {
            throw new RuntimeException("Bu id'de hayvan bulunamadı: " + id);
        }
    }

    //Değerlendirme Formu 11; Proje isterlerine göre hayvanın kaydedildiği metod
    public AnimalResponse createAnimal(AnimalRequest animalRequest) {
        Optional<Animal> optionalAnimal = animalRepo.findByName(animalRequest.getName());
        if (optionalAnimal.isPresent()){
            throw new RuntimeException("Bu isimde hayvan zaten var" + animalRequest.getName());
        }
        return animalMapper.asOutput(animalRepo.save(animalMapper.asEntity(animalRequest)));
    }
    /*public AnimalResponse animalUpdate(Long id, AnimalRequest request) {
        Optional<Animal> animalFromDb = animalRepo.findById(id);
        Optional<Animal> isAnimalExist = animalRepo.findByName(request.getName());

        if (animalFromDb.isEmpty()) {
            throw new RuntimeException(id + " Güncellemeye çalıştığınız hayvan sistemde yok.");
        }

        if (isAnimalExist.isPresent()) {
            throw new RuntimeException(id + " Bu hayvan daha önce sisteme kayıt olmuştur.");
        }
        Animal animal = animalFromDb.get();
        animalMapper.update(animal, request);
        return  animalMapper.asOutput(animalRepo.save(animal));

    }*/

    public AnimalResponse animalUpdate(Long id, AnimalRequest request) {
        Optional<Animal> animalFromDb = animalRepo.findById(id);
        Optional<Animal> isAnimalExist = animalRepo.findByName(request.getName());

        if (animalFromDb.isEmpty()) {
            throw new RuntimeException(id + " Güncellemeye çalıştığınız hayvan sistemde yok.");
        }

        if (isAnimalExist.isPresent() && !isAnimalExist.get().getId().equals(id)) {
            throw new RuntimeException(request.getName() + " İsimli hayvan daha önce sisteme kayıt olmuştur.");
        }

        Animal animal = animalFromDb.get();

        // İsim değişikliğini kontrol et ve ismi güncelle
        if (!animal.getName().equals(request.getName())) {
            animal.setName(request.getName());
        }

        // Diğer alanları güncelle
        animalMapper.update(animal, request);
        return animalMapper.asOutput(animalRepo.save(animal));
    }


    public void animalDeleteById(Long id) {
        Optional<Animal> animalFromDb = animalRepo.findById(id);
        if (animalFromDb.isPresent()) {
            animalRepo.delete(animalFromDb.get());
        }else {
            throw new RuntimeException(id +"id'li hayvan sistemde bulunamadı.");
        }
    }
}
