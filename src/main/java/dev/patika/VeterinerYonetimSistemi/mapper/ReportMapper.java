package dev.patika.VeterinerYonetimSistemi.mapper;

import dev.patika.VeterinerYonetimSistemi.dto.request.AnimalRequest;
import dev.patika.VeterinerYonetimSistemi.dto.request.ReportRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.AnimalResponse;
import dev.patika.VeterinerYonetimSistemi.dto.response.ReportResponse;
import dev.patika.VeterinerYonetimSistemi.entity.Animal;
import dev.patika.VeterinerYonetimSistemi.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface ReportMapper {
   ReportResponse asOutput(Report report);

    List<ReportResponse> asOutput(List<Report> report);
    Report asEntity(ReportRequest reportRequest);

    void updateReport(@MappingTarget Report entity, ReportRequest request);
}
