package com.duoc.msreservas.dto;

import com.duoc.msreservas.model.EstadoReserva;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private Long id;
    private LocalDate fechaReserva;
    private LocalDate fechaEntrega;
    private Double total;
    private Boolean activa;
    private Integer cantidadDias;

    private Long estadoReservaId;


}
