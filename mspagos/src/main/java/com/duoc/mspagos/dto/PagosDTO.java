package com.duoc.mspagos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagosDTO {

    private Long id;

    private Double monto;

    private String metodoPago;

    private Boolean pagado;

    private LocalDate fechaPago;

    private Integer numeroCuotas;

    private Long reservaId;


}
