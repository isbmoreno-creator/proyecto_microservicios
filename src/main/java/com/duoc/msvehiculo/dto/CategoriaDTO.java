package com.duoc.msvehiculo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private Integer cantidadVehiculos;
    private LocalDate fechaRegistro;

}
