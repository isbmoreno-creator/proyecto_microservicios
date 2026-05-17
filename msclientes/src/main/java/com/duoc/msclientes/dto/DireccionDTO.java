package com.duoc.msclientes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO {

    private Long id;
    private String calle;
    private String numero;
    private String comuna;
    private String region;
    private Integer codigoPostal;
    private Boolean activa;
    private LocalDate fechaRegistro;

}
