package com.java.eco_reportes_city.repository;

import com.java.eco_reportes_city.entity.PuntoReciclaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PuntoReciclajeRepository extends JpaRepository<PuntoReciclaje, Long> {

    List<PuntoReciclaje> findByTipoMaterialIgnoreCase(String tipoMaterial);
}