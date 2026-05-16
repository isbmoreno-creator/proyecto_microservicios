package com.duoc.msvehiculo.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="vehiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private Integer anio;
    private Double precioDiario;
    private Boolean disponible;
    private LocalDate fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
