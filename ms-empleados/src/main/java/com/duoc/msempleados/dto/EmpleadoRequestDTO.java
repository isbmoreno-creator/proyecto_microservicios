package com.duoc.msempleados.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmpleadoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    private String email;

    @NotBlank(message = "El cargo es obligatorio")
    @Size(min = 2, max = 100, message = "El cargo debe tener entre 2 y 100 caracteres")
    private String cargo;

    @NotNull(message = "Los años de experiencia son obligatorios")
    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    private Integer aniosExperiencia;

    @NotNull(message = "El salario es obligatorio")
    @DecimalMin(value = "0.0", message = "El salario no puede ser negativo")
    private Double salario;

    private boolean activo = true;

    @NotNull(message = "La fecha de contratación es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaContratacion;



}
