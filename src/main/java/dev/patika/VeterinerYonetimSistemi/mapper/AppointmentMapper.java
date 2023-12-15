package dev.patika.VeterinerYonetimSistemi.mapper;

import dev.patika.VeterinerYonetimSistemi.dto.request.AppointmentRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.AppointmentResponse;
import dev.patika.VeterinerYonetimSistemi.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AppointmentMapper {
    AppointmentResponse asOutput(Appointment appointment);
    List<AppointmentResponse> asOutput(List<Appointment> appointment);
    Appointment asEntity(AppointmentRequest appointmentRequest);

    void update(@MappingTarget Appointment entity, AppointmentRequest request);
}