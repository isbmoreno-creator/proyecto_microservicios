package com.duoc.msreservas.controller;

import com.duoc.msreservas.dto.EstadoReservaDTO;
import com.duoc.msreservas.dto.EstadoReservaRequestDTO;
import com.duoc.msreservas.service.EstadoReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estadoReserva")
public class EstadoReservaController {

    @Autowired
    private EstadoReservaService estadoReservaService;

    @GetMapping
    public ResponseEntity<List<EstadoReservaDTO>> listarEstadosReserva() {

        return ResponseEntity.ok(
                estadoReservaService.obtenerEstadosReserva());
    }

    @PostMapping
    public ResponseEntity<EstadoReservaDTO> guardarEstadoReserva(
            @Valid @RequestBody EstadoReservaRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estadoReservaService.guardarEstadoReserva(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoReservaDTO> obtenerEstadoReservaPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                estadoReservaService.obtenerEstadoReservaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoReservaDTO> actualizarEstadoReserva(
            @PathVariable Long id,
            @Valid @RequestBody EstadoReservaRequestDTO dto) {

        return ResponseEntity.ok(
                estadoReservaService.actualizarEstadoReserva(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstadoReserva(
            @PathVariable Long id) {

        estadoReservaService.eliminarEstadoReserva(id);

        return ResponseEntity.noContent().build();
    }
}
