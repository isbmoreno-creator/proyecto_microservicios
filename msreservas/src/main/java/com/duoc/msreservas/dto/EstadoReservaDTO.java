package com.duoc.msreservas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoReservaDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private Integer prioridad;
    private LocalDate fechaEstado;

    private Long reservaId;

}
