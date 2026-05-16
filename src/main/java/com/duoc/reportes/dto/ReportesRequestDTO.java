package com.duoc.dto;


import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ReportesRequestDTO {


    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 100, message = "El título debe ser al menos 3 y 100 caracteres de largo")
    private String titulo;

    @NotBlank(message = "Descripcion obligatoria")
    @Size(min = 5, max = 255, message = "debe ser al menos 5 y 255 caracteres de largo")
    private String descripcion;

    @NotBlank(message = "El tipo de reporte es obligatorio")
    @Size(min = 2, max = 50)
    private String tipoReporte;

    @NotNull(message = "El total de reservas es obligatorio")
    @Min(value = 0, message = "El total de reservas no puede ser negativo")
    private Integer totalReservas;

    @NotNull(message = "El monto total es obligatorio")
    @DecimalMin(value = "0.0", message = "El monto no debe ser negativo")
    private BigDecimal montoTotal;

    @NotNull(message = "La fecha de creacion es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaGeneracion;

    @NotNull(message = "El periodo de inicio es obligatorio")
    private LocalDate periodoInicio;

    @NotNull(message = "El periodo de finalizacion es obligatorio")
    private LocalDate periodoFin;
    private boolean activo = true;

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public Integer getTotalReservas() {
        return totalReservas;
    }

    public void setTotalReservas(Integer totalReservas) {
        this.totalReservas = totalReservas;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDate getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(LocalDate periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public LocalDate getPeriodoFin() {
        return periodoFin;
    }

    public void setPeriodoFin(LocalDate periodoFin) {
        this.periodoFin = periodoFin;
    }







}
