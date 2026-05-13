package com.duoc.msclientes.dto;

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
public class ClienteRequestDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String nombre;

    @NotBlank
    @Size(min = 2, max = 100)
    private String apellido;

    @NotBlank
    private String rut;

    @Email
    @NotBlank
    private String correo;

    @NotBlank
    private String telefono;

    @NotNull
    private Boolean activo;

    @NotNull
    private LocalDate fechaRegistro;

    @Positive
    private Integer puntos;

}
