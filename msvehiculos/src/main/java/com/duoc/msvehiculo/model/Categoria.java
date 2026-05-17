package com.duoc.msvehiculo.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private Integer cantidadVehiculos;
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "categoria")
    private List<Vehiculo> vehiculos;
}
