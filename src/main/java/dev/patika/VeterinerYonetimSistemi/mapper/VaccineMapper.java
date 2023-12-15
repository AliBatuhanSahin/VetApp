package dev.patika.VeterinerYonetimSistemi.mapper;
import dev.patika.VeterinerYonetimSistemi.dto.request.VaccineRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.VaccineResponse;
import dev.patika.VeterinerYonetimSistemi.entity.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface VaccineMapper {
    VaccineResponse asOutput(Vaccine vaccine);
    List<VaccineResponse> asOutput(List<Vaccine> vaccine);
    Vaccine asEntity(VaccineRequest vaccineRequest);

    void update(@MappingTarget Vaccine entity, VaccineRequest request);
}
