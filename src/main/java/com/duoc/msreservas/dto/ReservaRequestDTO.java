package com.duoc.msreservas.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequestDTO {

    @NotNull(message = "La fecha de reserva es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaReserva;

    @NotNull(message = "La fecha de entrega es obligatoria")
    private LocalDate fechaEntrega;

    @Positive(message = "El total debe ser positivo")
    private Double total;

    private Boolean activa;

    @Positive(message = "La cantidad de dias debe ser positiva")
    private Integer cantidadDias;

    @NotNull(message = "El estado de reserva es obligatorio")
    private Long estadoReservaId;
}
