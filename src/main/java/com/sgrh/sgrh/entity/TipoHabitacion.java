package com.sgrh.sgrh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tipo_habitacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer idTipo;

    @Column(name = "descripcion", length = 50, nullable = false)
    private String descripcion;

    @Column(name = "precio_base", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioBase;
}
