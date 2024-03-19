package dev.patika.VeterinerYonetimSistemi.service;
import dev.patika.VeterinerYonetimSistemi.dto.request.VaccineRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.AnimalResponse;
import dev.patika.VeterinerYonetimSistemi.dto.response.VaccineResponse;
import dev.patika.VeterinerYonetimSistemi.entity.Animal;
import dev.patika.VeterinerYonetimSistemi.entity.Vaccine;
import dev.patika.VeterinerYonetimSistemi.mapper.AnimalMapper;
import dev.patika.VeterinerYonetimSistemi.mapper.VaccineMapper;
import dev.patika.VeterinerYonetimSistemi.repository.IVaccineRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VaccineService {
    private final IVaccineRepo vaccineRepo;
    private final VaccineMapper vaccineMapper;
    private final AnimalMapper animalMapper;


    public List<VaccineResponse> findAll() {
        return vaccineMapper.asOutput(vaccineRepo.findAll());
    }

    public VaccineResponse getVaccineById(Long id){
        Optional<Vaccine> optionalVaccine = vaccineRepo.findById(id);
        if (optionalVaccine.isPresent()){
            return vaccineMapper.asOutput(optionalVaccine.get());
        }else {
            throw new RuntimeException("Bu id'de aşı bulunamadı: " + id);
        }
    }

    public List<Vaccine> getVaccinesByAnimalName(String animalName) {
        return vaccineRepo.findByAnimalName(animalName);
    }

    //Değerlendirme Formu 15; Proje isterlerine göre hayvana ait aşının kaydedildiği metod
    public VaccineResponse createVaccine(VaccineRequest vaccineRequest) {
        Optional<Vaccine> optionalVaccine = vaccineRepo.findByNameAndCode(vaccineRequest.getName(), vaccineRequest.getCode());

        if (optionalVaccine.isPresent()) {
            Vaccine existingVaccine = optionalVaccine.get();

            //Değerlendirme Formu 19; Proje isterlerine göre aşının koruyuculuk bitiş kontrolunun yapıldığı kod bloğu
            // Aşının koruyuculuk bitiş tarihi kontrol ediliyor
            LocalDate currentDate = LocalDate.now();
            LocalDate finishDate = existingVaccine.getProtectionFinishDate();

            if (finishDate != null && finishDate.isAfter(currentDate)) {
                throw new RuntimeException("Bu isimde aşı zaten var ve koruyuculuğu devam ediyor: " + vaccineRequest.getName());
            }
        }

        // Yeni aşı oluşturuluyor
        return vaccineMapper.asOutput(vaccineRepo.save(vaccineMapper.asEntity(vaccineRequest)));
    }

    //Değerlendirme Formu 20; Proje isterlerine göre hayvana ait tüm aşıları görüntüleyen metod
    public List<Vaccine> getVaccinesByAnimalId(String name) {
        // Belirli bir hayvana ait aşı kayıtlarını getirme işlemi
        return vaccineRepo.findVaccineByName(name);
    }

    //Değerlendirme Formu 21; Proje isterlerine göre hayvanların aşı kayıtlarının girilen tarih aralığına göre gösterildiği metod
    public List<AnimalResponse> getAnimalsWithUpcomingVaccines(LocalDate startDate, LocalDate endDate) {
        List<Vaccine> upcomingVaccines = vaccineRepo.findAnimalsWithUpcomingVaccines(startDate, endDate);
        List<Animal> animals = upcomingVaccines.stream().map(Vaccine::getAnimal).collect(Collectors.toList());
        return animalMapper.asOutput(animals);
    }

    public VaccineResponse vaccineUpdate(Long id, VaccineRequest request) {
        Optional<Vaccine> vaccineFromDb = vaccineRepo.findById(id);
        Optional<Vaccine> isVaccineExist = vaccineRepo.findByNameAndCode(request.getName(), request.getCode());

        if (vaccineFromDb.isEmpty()) {
            throw new RuntimeException(id + " Güncellemeye çalıştığınız aşı sistemde yok.");
        }

        if (isVaccineExist.isPresent()) {
            throw new RuntimeException(id + " Bu aşı daha önce sisteme kayıt olmuştur.");
        }
        Vaccine vaccine = vaccineFromDb.get();
        vaccineMapper.update(vaccine, request);
        return  vaccineMapper.asOutput(vaccineRepo.save(vaccine));

    }

    public void vaccineDeleteById(Long id) {
        Optional<Vaccine> vaccineFromDb = vaccineRepo.findById(id);
        if (vaccineFromDb.isPresent()) {
            vaccineRepo.delete(vaccineFromDb.get());
        }else {
            throw new RuntimeException(id +"id'li aşı sistemde bulunamadı.");
        }
    }

    public List<VaccineResponse> getUpcomingVaccines(LocalDate startDate, LocalDate endDate) {
        // startDate ve endDate parametrelerine göre yapılacak aşıları bulun ve döndürün
        List<VaccineResponse> upcomingVaccines = new ArrayList<>();
        // Örnek olarak, tüm aşıları döndürmek için:
        List<Vaccine> vaccines = vaccineRepo.findByProtectionFinishDateBetween(startDate, endDate);
        for (Vaccine vaccine : vaccines) {
            VaccineResponse vaccineResponse = mapToVaccineResponse(vaccine);
            upcomingVaccines.add(vaccineResponse);
        }
        return upcomingVaccines;
    }

    private VaccineResponse mapToVaccineResponse(Vaccine vaccine) {
        VaccineResponse response = new VaccineResponse();
        response.setId(vaccine.getId());
        response.setName(vaccine.getName());
        response.setCode(vaccine.getCode() != null ? vaccine.getCode() : "");
        response.setProtectionStartDate(vaccine.getProtectionStartDate() != null ? vaccine.getProtectionStartDate() : LocalDate.now());
        response.setProtectionFinishDate(vaccine.getProtectionFinishDate() != null ? vaccine.getProtectionFinishDate() : LocalDate.now());
        return response;
    }
}
