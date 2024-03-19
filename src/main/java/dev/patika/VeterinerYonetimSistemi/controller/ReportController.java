package dev.patika.VeterinerYonetimSistemi.controller;

import dev.patika.VeterinerYonetimSistemi.dto.request.AnimalRequest;
import dev.patika.VeterinerYonetimSistemi.dto.request.ReportRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.AnimalResponse;
import dev.patika.VeterinerYonetimSistemi.dto.response.ReportResponse;
import dev.patika.VeterinerYonetimSistemi.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@AllArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReportResponse> findAll() {
        return reportService.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ReportResponse createReport(@RequestBody ReportRequest reportRequest) {
        return reportService.createReport(reportRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReportResponse reportUpdate(@PathVariable Long id, @RequestBody ReportRequest request) {
        return reportService.reportUpdate(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void reportDelete(@PathVariable("id") Long id) {
        reportService.reportDeleteById(id);
    }
}
