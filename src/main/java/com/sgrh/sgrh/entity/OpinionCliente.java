package com.sgrh.sgrh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "opinion_cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpinionCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_opinion")
    private Integer idOpinion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
}
