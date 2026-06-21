package com.duoc.reportes.controller;


import com.duoc.reportes.dto.ReportesDTO;
import com.duoc.reportes.dto.ReportesRequestDTO;
import com.duoc.reportes.service.ReportesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReportesController {

    @Autowired
    private ReportesService reportesService;

    @GetMapping
    public ResponseEntity<List<ReportesDTO>> lista(){
        return ResponseEntity.ok(reportesService.obtenerTodos());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportesDTO> obtener(@PathVariable Integer id){
        ReportesDTO dto = reportesService.obtenerPorId(id);
        if (dto == null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ReportesDTO>> activosPorPeriodo(
            @RequestParam LocalDate desde,
            @RequestParam LocalDate hasta) {
        return ResponseEntity.ok(reportesService.obtenerActivosPorPeriodo(desde, hasta));
    }

    @PostMapping
    public ResponseEntity<ReportesDTO> guardar(@Valid @RequestBody ReportesRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reportesService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportesDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ReportesRequestDTO dto){
        ReportesDTO actualizado = reportesService.actualizar(id, dto);
        if (actualizado == null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReportesDTO> eliminar(@PathVariable Integer id){
        reportesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/consolidado")
    public ResponseEntity<Object> obtenerConsolidado(){
        return ResponseEntity.ok(reportesService.generarReporteConsolidado());
    }




}
