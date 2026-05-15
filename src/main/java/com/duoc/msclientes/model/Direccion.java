package com.duoc.msclientes.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="direccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle;
    private String numero;
    private String comuna;
    private String region;
    private Integer codigoPostal;
    private Boolean activa;
    private LocalDate fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
