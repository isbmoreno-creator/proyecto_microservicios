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
public class VehiculoDTO {

    private Long id;
    private String marca;
    private String modelo;
    private Integer anio;
    private Double precioDiario;
    private Boolean disponible;
    private LocalDate fechaIngreso;

    private Long categoriaId;

}
