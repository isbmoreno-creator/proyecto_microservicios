package com.duoc.reportes.service;



import com.duoc.reportes.dto.ReportesDTO;
import com.duoc.reportes.dto.ReportesRequestDTO;
import com.duoc.reportes.feing.PagoCliente;
import com.duoc.reportes.feing.ReservaCliente;
import com.duoc.reportes.model.ReportesModel;
import com.duoc.reportes.repository.ReportesRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportesService {

    private static final Logger log = LoggerFactory.getLogger(ReportesService.class);

    @Autowired
    private ReportesRespository reportesRespository;
    public List<ReportesDTO> obtenerTodos() {
        log.info("Obteniendo todos los reportes");
        return reportesRespository.findAll().stream()
                .map(this::convertir)
                .collect(Collectors.toList());

    }

    @Autowired
    private ReservaCliente reservaCliente;
    @Autowired
    private PagoCliente pagoCliente;


    public ReportesDTO obtenerPorId(Integer id) {
        log.info("Obteniendo por id: {}", id);
        ReportesModel reporte = reportesRespository.findById(id).orElse(null);
        if (reporte == null) {
            log.error("No se encontro reporte por el id: {}", id);
            return null;
        }
        return convertir(reporte);
    }
    public ReportesDTO guardar(ReportesRequestDTO dto) {
        log.info("Guardando nuevo reporte");
        ReportesModel reporte = new ReportesModel();
            reporte.setTitulo(dto.getTitulo());
            reporte.setDescripcion(dto.getDescripcion());
            reporte.setTipoReporte(dto.getTipoReporte());
            reporte.setTotalReservas(dto.getTotalReservas());
            reporte.setMontoTotal(dto.getMontoTotal());
            reporte.setActivo(dto.isActivo());
            reporte.setFechaGeneracion(dto.getFechaGeneracion());
            reporte.setPeriodoInicio(dto.getPeriodoInicio());
            reporte.setPeriodoFin(dto.getPeriodoFin());
            return convertir(reportesRespository.save(reporte));


        }
    public ReportesDTO actualizar(Integer id, ReportesRequestDTO dto){

        log.info("Actualizando reportes por id: {}", id);
        ReportesModel reporte = reportesRespository.findById(id).orElse(null);
        if (reporte == null) {
            log.error("No se encontro reporte por el id: {}", id);
            return null;
        }
            reporte.setTitulo(dto.getTitulo());
            reporte.setDescripcion(dto.getDescripcion());
            reporte.setTipoReporte(dto.getTipoReporte());
            reporte.setTotalReservas(dto.getTotalReservas());
            reporte.setMontoTotal(dto.getMontoTotal());
            reporte.setActivo(dto.isActivo());
            reporte.setFechaGeneracion(dto.getFechaGeneracion());
            reporte.setPeriodoInicio(dto.getPeriodoInicio());
            reporte.setPeriodoFin(dto.getPeriodoFin());
            return convertir(reportesRespository.save(reporte));
        }

    public void eliminar(Integer id){
        log.info("Eliminando reportes por id: {}", id);
        reportesRespository.deleteById(id);
        }
    private ReportesDTO convertir(ReportesModel reporte){
        ReportesDTO dto = new ReportesDTO();
            dto.setId(reporte.getId());
            dto.setTitulo(reporte.getTitulo());
            dto.setDescripcion(reporte.getDescripcion());
            dto.setTipoReporte(reporte.getTipoReporte());
            dto.setTotalReservas(reporte.getTotalReservas());
            dto.setMontoTotal(reporte.getMontoTotal());
            dto.setActivo(reporte.isActivo());
            dto.setFechaGeneracion(reporte.getFechaGeneracion());
            dto.setPeriodoInicio(reporte.getPeriodoInicio());
            dto.setPeriodoFin(reporte.getPeriodoFin());
            return dto;
        }

    public Object generarReporteConsolidado(){
        log.info("Generando reporte consolidado");
        List<Object> reservas = reservaCliente.obtenerReservas();
        List<Object> pagos = pagoCliente.obtenerPagos();

        Map<String, Object> consolidado = new HashMap<>();
        consolidado.put("reservas", reservas);
        consolidado.put("pagos", pagos);
        consolidado.put("totalReservas", reservas.size());
        consolidado.put("totalPagos", pagos.size());

        return consolidado;
    }






    }

