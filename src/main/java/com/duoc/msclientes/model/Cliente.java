package com.duoc.msclientes.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String rut;
    private String correo;
    private String telefono;
    private Integer puntos;
    private Boolean activo;
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "cliente")
    private List<Direccion> direcciones;

}
