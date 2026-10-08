package com.java.eco_reportes_city.repository;

import com.java.eco_reportes_city.entity.EstadoReporte;
import com.java.eco_reportes_city.entity.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    List<Reporte> findByEstado(EstadoReporte estado);

    List<Reporte> findByUsuarioId(Long usuarioId);
}