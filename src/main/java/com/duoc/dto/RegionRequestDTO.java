package com.duoc.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class RegionRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 10, message = "El nombre debe ser almenos de 2 a 100 caracteres de largo")
    private String nombre;

    @NotBlank(message ="El codigo es obligatorio ")
    @Size(min = 2, max = 10, message = "El codigo debe ser de almenos 2 a 10 caracteres")
    private String codigo;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 5, max = 255, message = "La descripcion debe ser de almenos 5 a 255 caracteres")
    private String descripcion;

    @NotBlank
    @Min(value = 1, message = "El valor debe ser mayor a 0")
    private Integer numeroRegion;

    @NotBlank(message = "Debe indicar la zona geografica")
    @Size(min = 2, max = 50)
    private String zonaGeografica;

    private boolean activo;

    @NotBlank(message = "Debe indicar la fecha de creacion")
    @PastOrPresent(message = "indique la fecha correcta")
    private LocalDate fechaCreacion;

    public @NotBlank(message = "El nombre es obligatorio") @Size(min = 2, max = 10, message = "El nombre debe ser almenos de 2 a 100 caracteres de largo") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre es obligatorio") @Size(min = 2, max = 10, message = "El nombre debe ser almenos de 2 a 100 caracteres de largo") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "El codigo es obligatorio ") @Size(min = 2, max = 10, message = "El codigo debe ser de almenos 2 a 10 caracteres") String getCodigo() {
        return codigo;
    }

    public void setCodigo(@NotBlank(message = "El codigo es obligatorio ") @Size(min = 2, max = 10, message = "El codigo debe ser de almenos 2 a 10 caracteres") String codigo) {
        this.codigo = codigo;
    }

    public @NotBlank(message = "La descripcion es obligatoria") @Size(min = 5, max = 255, message = "La descripcion debe ser de almenos 5 a 255 caracteres") String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotBlank(message = "La descripcion es obligatoria") @Size(min = 5, max = 255, message = "La descripcion debe ser de almenos 5 a 255 caracteres") String descripcion) {
        this.descripcion = descripcion;
    }

    public @NotBlank @Min(value = 1, message = "El valor debe ser mayor a 0") Integer getNumeroRegion() {
        return numeroRegion;
    }

    public void setNumeroRegion(@NotBlank @Min(value = 1, message = "El valor debe ser mayor a 0") Integer numeroRegion) {
        this.numeroRegion = numeroRegion;
    }

    public @NotBlank(message = "Debe indicar la zona geografica") @Size(min = 2, max = 50) String getZonaGeografica() {
        return zonaGeografica;
    }

    public void setZonaGeografica(@NotBlank(message = "Debe indicar la zona geografica") @Size(min = 2, max = 50) String zonaGeografica) {
        this.zonaGeografica = zonaGeografica;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public @NotBlank(message = "Debe indicar la fecha de creacion") @PastOrPresent(message = "indique la fecha correcta") LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(@NotBlank(message = "Debe indicar la fecha de creacion") @PastOrPresent(message = "indique la fecha correcta") LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
