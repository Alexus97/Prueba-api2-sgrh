package com.sgrh.sgrh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleado") 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    
    @Column(name = "nombre_completo", length = 100, nullable = false)
    private String nombreCompleto;

    @Column(name = "tipo_documento", length = 10, nullable = false)
    private String tipoDocumento;


    @Column(name = "numero_documento", length = 20, nullable = false)
    private String numeroDocumento;


    @Column(name = "cargo", length = 50, nullable = false)
    private String cargo;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;
}