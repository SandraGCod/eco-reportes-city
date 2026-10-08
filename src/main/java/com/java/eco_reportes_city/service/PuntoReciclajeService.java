package com.java.eco_reportes_city.service;

import com.java.eco_reportes_city.dto.PuntoReciclajeRequest;
import com.java.eco_reportes_city.dto.PuntoReciclajeResponse;
import com.java.eco_reportes_city.entity.PuntoReciclaje;
import com.java.eco_reportes_city.exception.RecursoNoEncontradoException;
import com.java.eco_reportes_city.repository.PuntoReciclajeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PuntoReciclajeService {

    private final PuntoReciclajeRepository repository;

    public PuntoReciclajeService(PuntoReciclajeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PuntoReciclajeResponse crear(PuntoReciclajeRequest req) {
        PuntoReciclaje punto = PuntoReciclaje.builder().build();
        aplicar(punto, req);
        return aRespuesta(repository.save(punto));
    }

    @Transactional(readOnly = true)
    public List<PuntoReciclajeResponse> listar(String tipoMaterial) {
        List<PuntoReciclaje> puntos = (tipoMaterial == null || tipoMaterial.isBlank())
                ? repository.findAll()
                : repository.findByTipoMaterialIgnoreCase(tipoMaterial);
        return puntos.stream().map(this::aRespuesta).toList();
    }

    @Transactional(readOnly = true)
    public PuntoReciclajeResponse obtener(Long id) {
        return aRespuesta(buscar(id));
    }

    @Transactional
    public PuntoReciclajeResponse actualizar(Long id, PuntoReciclajeRequest req) {
        PuntoReciclaje punto = buscar(id);
        aplicar(punto, req);
        return aRespuesta(repository.save(punto));
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }

    private PuntoReciclaje buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Punto de reciclaje no encontrado: " + id));
    }

    private void aplicar(PuntoReciclaje punto, PuntoReciclajeRequest req) {
        punto.setNombre(req.nombre());
        punto.setTipoMaterial(req.tipoMaterial());
        punto.setLatitud(req.latitud());
        punto.setLongitud(req.longitud());
        punto.setDireccion(req.direccion());
        punto.setHorario(req.horario());
    }

    private PuntoReciclajeResponse aRespuesta(PuntoReciclaje p) {
        return new PuntoReciclajeResponse(
                p.getId(), p.getNombre(), p.getTipoMaterial(),
                p.getLatitud(), p.getLongitud(), p.getDireccion(), p.getHorario());
    }
}