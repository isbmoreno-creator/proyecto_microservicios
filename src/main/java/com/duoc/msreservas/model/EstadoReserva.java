package com.duoc.msreservas.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="estado_reserva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Boolean activo;
    private Integer prioridad;
    private LocalDate fechaEstado;

}
