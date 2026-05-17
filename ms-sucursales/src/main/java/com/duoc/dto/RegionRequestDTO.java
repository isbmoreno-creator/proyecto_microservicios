package com.duoc.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegionRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe ser almenos de 2 a 100 caracteres de largo")
    private String nombre;

    @NotBlank(message = "El codigo es obligatorio ")
    @Size(min = 2, max = 10, message = "El codigo debe ser de almenos 2 a 10 caracteres")
    private String codigo;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 5, max = 255, message = "La descripcion debe ser de almenos 5 a 255 caracteres")
    private String descripcion;

    @NotNull(message = "El numero es obligatorio")
    @Min(value = 1, message = "El valor debe ser mayor a 0")
    private Integer numeroRegion;

    @NotBlank(message = "Debe indicar la zona geografica")
    @Size(min = 2, max = 50)
    private String zonaGeografica;


    private boolean activo;

    @NotNull(message = "Debe indicar la fecha de creacion")
    @PastOrPresent(message = "indique la fecha correcta")
    private LocalDate fechaCreacion;



}
