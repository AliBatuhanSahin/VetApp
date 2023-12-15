package dev.patika.VeterinerYonetimSistemi.service;

import dev.patika.VeterinerYonetimSistemi.dto.request.AppointmentRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.AppointmentResponse;
import dev.patika.VeterinerYonetimSistemi.dto.response.AvailableDateResponse;
import dev.patika.VeterinerYonetimSistemi.dto.response.DoctorResponse;
import dev.patika.VeterinerYonetimSistemi.entity.Appointment;
import dev.patika.VeterinerYonetimSistemi.entity.AvailableDate;
import dev.patika.VeterinerYonetimSistemi.entity.Doctor;
import dev.patika.VeterinerYonetimSistemi.mapper.AppointmentMapper;
import dev.patika.VeterinerYonetimSistemi.repository.IAppointmentRepo;
import dev.patika.VeterinerYonetimSistemi.repository.IAvailableDateRepo;
import dev.patika.VeterinerYonetimSistemi.repository.IDoctorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final IAppointmentRepo appointmentRepo;
    private final AppointmentMapper appointmentMapper;
    private final IAvailableDateRepo availableDateRepo;


    public List<AppointmentResponse> findAll() {
        return appointmentMapper.asOutput(appointmentRepo.findAll());
    }

    public AppointmentResponse getAppointmentById(Long id){
        Optional<Appointment> optionalAppointment = appointmentRepo.findById(id);
        if (optionalAppointment.isPresent()){
            return appointmentMapper.asOutput(optionalAppointment.get());
        }else {
            throw new RuntimeException("Bu id'de randevu bulunamadı: " + id);
        }
    }

    //Değerlendirme Formu 14; Proje isterlerine göre randevunun kaydedildiği metod
    public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest) {
        LocalDateTime requestedDateTime = appointmentRequest.getAppointmentDate();
        Doctor requestedDoctor = appointmentRequest.getDoctor();

        //Değerlendirme Formu 22; Proje isterlerine göre randevu oluşturulurken doktorun müsait gününün kontrol edildiği kod bloğ
        // 1. Doktorun o tarihte çalışıp çalışmadığı kontrol ediliyor.
        if (!isDoctorAvailable(requestedDoctor, requestedDateTime)) {
            throw new RuntimeException("Doktor bu tarihte çalışmamaktadır!");
        }

        //Değerlendirme Formu 22; Proje isterlerine göre randevu oluşturulurken doktorun o saatte başka randevusu var mı kontrolü yapılan kod bloğ
        // 2. Doktorun belirtilen saatte başka bir randevusu var mı kontrol ediliyor.
        if (isAppointmentTimeTaken(requestedDoctor, requestedDateTime)) {
            throw new RuntimeException("Girilen saatte başka bir randevu mevcuttur.");
        }

        return appointmentMapper.asOutput(appointmentRepo.save(appointmentMapper.asEntity(appointmentRequest)));
    }


    private boolean isDoctorAvailable(Doctor doctor, LocalDateTime requestedDateTime) {
        List<AvailableDate> availableDates = availableDateRepo.findByDoctorId(doctor.getId());

        if (availableDates != null && !availableDates.isEmpty()) {
            // Belirtilen tarihin hangi güne denk geldiğini al
            DayOfWeek requestedDayOfWeek = requestedDateTime.getDayOfWeek();

            // Belirtilen tarihte doktorun çalışma günlerine bak
            boolean isWorkingDay = availableDates.stream()
                    .anyMatch(date -> date.getAvailableDate().getDayOfWeek().equals(requestedDayOfWeek));

            // Eğer doktor o tarihte çalışıyorsa true döndür
            return isWorkingDay;
        }
        return false;
    }


    private boolean isAppointmentTimeTaken(Doctor doctor, LocalDateTime requestedDateTime) {
        LocalDateTime startOfDay = requestedDateTime.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusHours(23).plusMinutes(59); // Günün sonu olarak düşünülen saat
        LocalDateTime startOfHour = requestedDateTime.withMinute(0).withSecond(0);
        LocalDateTime endOfHour = startOfHour.plusHours(1);

        List<Appointment> doctorAppointmentsForDay = appointmentRepo.findByDoctorIdAndAppointmentDateBetween(
                doctor.getId(), startOfDay, endOfDay);

        List<Appointment> doctorAppointmentsForHour = appointmentRepo.findByDoctorIdAndAppointmentDateBetween(
                doctor.getId(), startOfHour, endOfHour);

        // Belirtilen saatte doktorun randevusu var mı kontrol edilir.
        boolean isAppointmentTakenForDay = doctorAppointmentsForDay.stream()
                .map(Appointment::getAppointmentDate)
                .anyMatch(appointmentDateTime -> appointmentDateTime.toLocalTime().equals(requestedDateTime.toLocalTime()));

        boolean isAppointmentTakenForHour = !doctorAppointmentsForHour.isEmpty();

        return isAppointmentTakenForDay || isAppointmentTakenForHour;
    }

    //Değerlendirme Formu 24; Proje isterlerine göre randevu oluşturulurken kullanıcı tarafından girilen tarih aralığına ve doktora göre filtrelendiği metod
    public List<AppointmentResponse> getAppointmentsByDoctorAndDateRange(Long doctorId, LocalDate startDate, LocalDate endDate) {
        // Verilen doktor ve tarih aralığına göre randevuları getir
        List<Appointment> appointments = appointmentRepo.findByDoctorIdAndAppointmentDateBetween(doctorId, startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
        return appointmentMapper.asOutput(appointments);
    }

    //Değerlendirme Formu 23; Proje isterlerine göre randevu oluşturulurken kullanıcı tarafından girilen tarih aralığına ve hayvana göre filtrelendiği metod
    public List<AppointmentResponse> getAppointmentsByAnimalAndDateRange(Long animalId, LocalDate startDate, LocalDate endDate) {
        // Verilen hayvan ve tarih aralığına göre randevuları getir
        List<Appointment> appointments = appointmentRepo.findByAnimalIdAndAppointmentDateBetween(animalId, startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
        return appointmentMapper.asOutput(appointments);
    }

    public AppointmentResponse appointmentUpdate(Long id, AppointmentRequest request) {
        Optional<Appointment> appointmentFromDb = appointmentRepo.findById(id);
        Optional<Appointment> isAppointmentExist = appointmentRepo.findByAppointmentDateAndAnimalIdAndDoctorId(
                request.getAppointmentDate(),
                request.getAnimal().getId(),
                request.getDoctor().getId());

        if (appointmentFromDb.isEmpty()) {
            throw new RuntimeException(id + " Güncellemeye çalıştığınız randevu sistemde yok.");
        }

        if (isAppointmentExist.isPresent()) {
            throw new RuntimeException(id + " Bu randevu daha önce sisteme kayıt olmuştur.");
        }
        Appointment appointment = appointmentFromDb.get();
        appointmentMapper.update(appointment, request);
        return  appointmentMapper.asOutput(appointmentRepo.save(appointment));

    }

    public void appointmentDeleteById(Long id) {
        Optional<Appointment> appointmentFromDb = appointmentRepo.findById(id);
        if (appointmentFromDb.isPresent()) {
            appointmentRepo.delete(appointmentFromDb.get());
        }else {
            throw new RuntimeException(id +"id'li randevu sistemde bulunamadı.");
        }
    }
}
