package com.duoc.mspagos.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double monto;

    private String metodoPago;

    private Boolean pagado;

    private LocalDate fechaPago;

    private Integer numeroCuotas;

    private Long reservaId;
}
