package com.duoc.msclientes.model;
import jakarta.persistence.*;
import lombok.*;
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

    @OneToOne
    @JoinColumn(name ="direccion_id")
    private Direccion direccion;

}
