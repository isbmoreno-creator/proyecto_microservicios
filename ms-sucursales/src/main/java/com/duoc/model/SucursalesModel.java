package com.duoc.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "sucursal")
public class SucursalesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;
    private String numero;
    private Integer capacidad;
    private Double costoArriendo;
    private boolean operativa;
    private LocalDate fechaApertura;


    @ManyToOne
    @JoinColumn(name = "region_id")
    private RegionModel region;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Double getCostoArriendo() {
        return costoArriendo;
    }

    public void setCostoArriendo(Double costoArriendo) {
        this.costoArriendo = costoArriendo;
    }

    public boolean isOperativa() {
        return operativa;
    }

    public void setOperativa(boolean operativa) {
        this.operativa = operativa;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public RegionModel getRegion() {
        return region;
    }

    public void setRegion(RegionModel region) {
        this.region = region;
    }
}
