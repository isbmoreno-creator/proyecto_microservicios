package com.duoc.msreservas.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="reserva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaReserva;
    private LocalDate fechaEntrega;
    private Double total;
    private Boolean activa;
    private Integer cantidadDias;

    @ManyToOne
    @JoinColumn(name = "estado_reserva_id")
    private EstadoReserva estadoReserva;
}
