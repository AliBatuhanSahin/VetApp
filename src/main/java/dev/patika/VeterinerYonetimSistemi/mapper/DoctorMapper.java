package dev.patika.VeterinerYonetimSistemi.mapper;

import dev.patika.VeterinerYonetimSistemi.dto.request.DoctorRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.DoctorResponse;
import dev.patika.VeterinerYonetimSistemi.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper
public interface DoctorMapper {
    DoctorResponse asOutput(Doctor animal);
    List<DoctorResponse> asOutput(List<Doctor> doctor);
    Doctor asEntity(DoctorRequest animalRequest);

    void update(@MappingTarget Doctor entity, DoctorRequest request);
}
