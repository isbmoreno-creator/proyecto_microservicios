package com.duoc.mspagos.dto;

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
public class PagosRequestDTO {

    @DecimalMin("0.0")
    @Positive
    private Double monto;

    @NotBlank
    @Size(min = 2, max = 50)
    private String metodoPago;

    @NotNull
    private Boolean pagado;

    @NotNull
    @PastOrPresent
    private LocalDate fechaPago;

    @Positive
    private Integer numeroCuotas;

    @NotNull
    private Long reservaId;
}
