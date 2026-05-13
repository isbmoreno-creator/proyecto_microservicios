package com.duoc.msclientes.model;
import jakarta.persistence.*;
import lombok.*;

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
    private String ciudad;
}
