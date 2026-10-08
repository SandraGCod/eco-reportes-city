package com.java.eco_reportes_city.controller;

import com.java.eco_reportes_city.dto.PuntoReciclajeRequest;
import com.java.eco_reportes_city.dto.PuntoReciclajeResponse;
import com.java.eco_reportes_city.service.PuntoReciclajeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puntos-reciclaje")
public class PuntoReciclajeController {

    private final PuntoReciclajeService service;

    public PuntoReciclajeController(PuntoReciclajeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PuntoReciclajeResponse> crear(@Valid @RequestBody PuntoReciclajeRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(req));
    }

    @GetMapping
    public List<PuntoReciclajeResponse> listar(@RequestParam(required = false) String tipoMaterial) {
        return service.listar(tipoMaterial);
    }

    @GetMapping("/{id}")
    public PuntoReciclajeResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PutMapping("/{id}")
    public PuntoReciclajeResponse actualizar(@PathVariable Long id, @Valid @RequestBody PuntoReciclajeRequest req) {
        return service.actualizar(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}