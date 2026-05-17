package com.duoc.msvehiculo.dto;

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
public class CategoriaRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100)
    private String nombre;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 5, max = 200)
    private String descripcion;

    private Boolean activo;

    @Positive(message = "La cantidad debe ser positiva")
    private Integer cantidadVehiculos;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaRegistro;
}
