package com.java.eco_reportes_city.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "puntos_reciclaje")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PuntoReciclaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String tipoMaterial; // plástico, vidrio, papel, orgánico...

    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;

    private String direccion;

    private String horario;
}