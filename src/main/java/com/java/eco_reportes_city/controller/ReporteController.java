package com.java.eco_reportes_city.controller;

import com.java.eco_reportes_city.dto.CambioEstadoRequest;
import com.java.eco_reportes_city.dto.ReporteRequest;
import com.java.eco_reportes_city.dto.ReporteResponse;
import com.java.eco_reportes_city.entity.EstadoReporte;
import com.java.eco_reportes_city.service.ReporteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @PostMapping
    public ResponseEntity<ReporteResponse> crear(@Valid @RequestBody ReporteRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteService.crear(req));
    }

    @GetMapping
    public List<ReporteResponse> listar(@RequestParam(required = false) EstadoReporte estado) {
        return reporteService.listar(estado);
    }

    @GetMapping("/{id}")
    public ReporteResponse obtener(@PathVariable Long id) {
        return reporteService.obtener(id);
    }

    @PatchMapping("/{id}/estado")
    public ReporteResponse cambiarEstado(@PathVariable Long id, @Valid @RequestBody CambioEstadoRequest req) {
        return reporteService.cambiarEstado(id, req.estado());
    }
}