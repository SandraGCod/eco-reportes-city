package com.java.eco_reportes_city.service;

import com.java.eco_reportes_city.dto.ReporteRequest;
import com.java.eco_reportes_city.dto.ReporteResponse;
import com.java.eco_reportes_city.entity.EstadoReporte;
import com.java.eco_reportes_city.entity.Reporte;
import com.java.eco_reportes_city.entity.User;
import com.java.eco_reportes_city.exception.RecursoNoEncontradoException;
import com.java.eco_reportes_city.repository.ReporteRepository;
import com.java.eco_reportes_city.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReporteService {

    private final ReporteRepository reporteRepository;
    private final UserRepository userRepository;

    public ReporteService(ReporteRepository reporteRepository, UserRepository userRepository) {
        this.reporteRepository = reporteRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ReporteResponse crear(ReporteRequest req, Long usuarioId) {
        User usuario = userRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado: " + usuarioId));

        Reporte reporte = Reporte.builder()
                .descripcion(req.descripcion())
                .fotoUrl(req.fotoUrl())
                .latitud(req.latitud())
                .longitud(req.longitud())
                .direccion(req.direccion())
                .usuario(usuario)
                .build();

        return aRespuesta(reporteRepository.save(reporte));
    }

    @Transactional(readOnly = true)
    public List<ReporteResponse> listar(EstadoReporte estado) {
        List<Reporte> reportes = (estado == null)
                ? reporteRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaCreacion"))
                : reporteRepository.findByEstadoOrderByFechaCreacionDesc(estado);
        return reportes.stream().map(this::aRespuesta).toList();
    }

    @Transactional(readOnly = true)
    public ReporteResponse obtener(Long id) {
        return aRespuesta(buscar(id));
    }

    @Transactional
    public ReporteResponse cambiarEstado(Long id, EstadoReporte nuevoEstado) {
        Reporte reporte = buscar(id);
        reporte.setEstado(nuevoEstado);
        return aRespuesta(reporteRepository.save(reporte));
    }

    private Reporte buscar(Long id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Reporte no encontrado: " + id));
    }

    private ReporteResponse aRespuesta(Reporte r) {
        return new ReporteResponse(
                r.getId(), r.getDescripcion(), r.getFotoUrl(), r.getLatitud(), r.getLongitud(),
                r.getDireccion(), r.getEstado(), r.getFechaCreacion(),
                r.getUsuario().getId(), r.getUsuario().getNombre());
    }
}