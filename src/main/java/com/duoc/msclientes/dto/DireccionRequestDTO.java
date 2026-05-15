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
public class DireccionRequestDTO {

    @NotBlank(message = "La calle es obligatoria")
    @Size(min = 2, max = 100)
    private String calle;

    @NotBlank(message = "El número es obligatorio")
    @Size(min = 1, max = 10)
    private String numero;

    @NotBlank(message = "La comuna es obligatoria")
    @Size(min = 2, max = 100)
    private String comuna;

    @NotBlank(message = "La región es obligatoria")
    @Size(min = 2, max = 100)
    private String region;

    @Positive(message = "El código postal debe ser positivo")
    private Integer codigoPostal;

    private Boolean activa;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaRegistro;
}
