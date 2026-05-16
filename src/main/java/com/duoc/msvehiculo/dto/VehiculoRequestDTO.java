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
public class VehiculoRequestDTO {

    @NotBlank(message = "La marca es obligatoria")
    @Size(min = 2, max = 100)
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(min = 2, max = 100)
    private String modelo;

    @Positive(message = "El año debe ser positivo")
    private Integer anio;

    @Positive(message = "El precio debe ser positivo")
    private Double precioDiario;

    private Boolean disponible;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaIngreso;

    @NotNull(message = "La categoria es obligatoria")
    private Long categoriaId;
}
