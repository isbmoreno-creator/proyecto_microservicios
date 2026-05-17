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
public class EstadoReservaRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100)
    private String nombre;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 5, max = 200)
    private String descripcion;

    private Boolean activo;

    @Positive(message = "La prioridad debe ser positiva")
    private Integer prioridad;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaEstado;

    @NotNull(message = "La reserva es obligatoria")
    private Long reservaId;
}
