package com.duoc.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class SucursalesRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe ser almenos de 2 a 100 caracteres de largo")
    private String nombre;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 5, max = 255, message = "La descripcion debe ser de almenos 5 a 255 caracteres")
    private String descripcion;

    @NotNull(message = "El numero es obligatorio")
    @Min(value = 1, message = "El valor debe ser mayor a 0")
    private Integer numero;

    @NotNull(message = "Debe ingresar la capacidad de la sucursal")
    @Min(value = 1, message = "El valor debe ser mayor a 0")
    private Integer capacidad;

    @NotNull(message = "El costo de arriendo es obligatorio")
    @DecimalMin(value = "0.0", message = "El costo no puede ser negativo")
    private Double costoArriendo;


    private boolean activo;

    @NotNull(message = "La fecha de apertura es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaApertura;

    @NotNull(message = "La región es obligatoria")
    private Integer regionId;



}
