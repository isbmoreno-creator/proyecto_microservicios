package com.duoc.reportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.duoc.reportes.model.ReportesModel;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportesRespository extends JpaRepository <ReportesModel, Integer> {

    @Query(value = """
            SELECT *
            FROM reporte
            WHERE activo = true
              AND fecha_generacion BETWEEN :desde AND :hasta
            ORDER BY fecha_generacion DESC
            """, nativeQuery = true)
    List<ReportesModel> buscarReportesActivosPorPeriodo(LocalDate desde, LocalDate hasta);
}
