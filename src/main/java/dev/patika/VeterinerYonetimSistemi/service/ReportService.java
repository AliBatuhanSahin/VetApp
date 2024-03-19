package dev.patika.VeterinerYonetimSistemi.service;

import dev.patika.VeterinerYonetimSistemi.dto.request.AnimalRequest;
import dev.patika.VeterinerYonetimSistemi.dto.request.ReportRequest;
import dev.patika.VeterinerYonetimSistemi.dto.request.VaccineRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.AnimalResponse;
import dev.patika.VeterinerYonetimSistemi.dto.response.ReportResponse;
import dev.patika.VeterinerYonetimSistemi.dto.response.VaccineResponse;
import dev.patika.VeterinerYonetimSistemi.entity.Animal;
import dev.patika.VeterinerYonetimSistemi.entity.Report;
import dev.patika.VeterinerYonetimSistemi.mapper.ReportMapper;
import dev.patika.VeterinerYonetimSistemi.repository.IReportRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final IReportRepo reportRepo;
    private final ReportMapper reportMapper;



    public List<ReportResponse> findAll() {
        return reportMapper.asOutput(reportRepo.findAll());
    }

    public ReportResponse createReport(ReportRequest reportRequest) {
        Optional<Report> optionalReport = reportRepo.findByTitle(reportRequest.getTitle());
        if (optionalReport.isPresent()){
            throw new RuntimeException("Bu isimde hayvan zaten var" + reportRequest.getTitle());
        }
        return reportMapper.asOutput(reportRepo.save(reportMapper.asEntity(reportRequest)));
    }

    public ReportResponse reportUpdate(Long id, ReportRequest request) {
        Optional<Report> reportFromDb = reportRepo.findById(id);
        Optional<Report> isReportExist = reportRepo.findByTitle(request.getTitle());

        if (reportFromDb.isEmpty()) {
            throw new RuntimeException(id + " Güncellemeye çalıştığınız hayvan sistemde yok.");
        }

        if (isReportExist.isPresent()) {
            throw new RuntimeException(id + " Bu hayvan daha önce sisteme kayıt olmuştur.");
        }
        Report report = reportFromDb.get();
        reportMapper.updateReport(report, request);
        return  reportMapper.asOutput(reportRepo.save(report));

    }

    public void reportDeleteById(Long id) {
        Optional<Report> reportFromDb = reportRepo.findById(id);
        if (reportFromDb.isPresent()) {
            reportRepo.delete(reportFromDb.get());
        }else {
            throw new RuntimeException(id +"id'li hayvan sistemde bulunamadı.");
        }
    }
}
