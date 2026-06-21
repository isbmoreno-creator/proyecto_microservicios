package com.duoc.reportes.runner;

import com.duoc.reportes.model.ReportesModel;
import com.duoc.reportes.repository.ReportesRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final ReportesRespository reportesRespository;

    public DataLoader(ReportesRespository reportesRespository) {
        this.reportesRespository = reportesRespository;
    }

    @Override
    public void run(String... args) {
        if (reportesRespository.count() > 0) {
            return;
        }

        reportesRespository.save(crearReporte(
                "Reporte Enero",
                "Reporte del mes de enero",
                "MENSUAL",
                10,
                new BigDecimal("50000"),
                LocalDate.of(2025, 1, 31),
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 1, 31)
        ));

        reportesRespository.save(crearReporte(
                "Reporte Febrero",
                "Reporte del mes de febrero",
                "MENSUAL",
                15,
                new BigDecimal("750000"),
                LocalDate.of(2025, 2, 28),
                LocalDate.of(2025, 2, 1),
                LocalDate.of(2025, 2, 28)
        ));

        reportesRespository.save(crearReporte(
                "Reporte Marzo",
                "Reporte del mes de marzo",
                "MENSUAL",
                20,
                new BigDecimal("1000000"),
                LocalDate.of(2025, 3, 31),
                LocalDate.of(2025, 3, 1),
                LocalDate.of(2025, 3, 31)
        ));
    }

    private ReportesModel crearReporte(
            String titulo,
            String descripcion,
            String tipoReporte,
            Integer totalReservas,
            BigDecimal montoTotal,
            LocalDate fechaGeneracion,
            LocalDate periodoInicio,
            LocalDate periodoFin
    ) {
        ReportesModel reporte = new ReportesModel();
        reporte.setTitulo(titulo);
        reporte.setDescripcion(descripcion);
        reporte.setTipoReporte(tipoReporte);
        reporte.setTotalReservas(totalReservas);
        reporte.setMontoTotal(montoTotal);
        reporte.setActivo(true);
        reporte.setFechaGeneracion(fechaGeneracion);
        reporte.setPeriodoInicio(periodoInicio);
        reporte.setPeriodoFin(periodoFin);
        return reporte;
    }
}
