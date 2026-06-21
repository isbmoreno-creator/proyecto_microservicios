package com.duoc.msreservas.controller;


import com.duoc.msreservas.dto.ReservaDTO;
import com.duoc.msreservas.dto.ReservaRequestDTO;
import com.duoc.msreservas.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarReservas() {

        return ResponseEntity.ok(reservaService.obtenerReservas());
    }

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<ReservaDTO>> listarReservasPorSucursal(
            @PathVariable Integer sucursalId) {

        return ResponseEntity.ok(
                reservaService.obtenerReservasPorSucursal(sucursalId));
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> guardarReserva(
            @Valid @RequestBody ReservaRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaService.guardarReserva(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> obtenerReservaPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                reservaService.obtenerReservaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> actualizarReserva(
            @PathVariable Long id,
            @Valid @RequestBody ReservaRequestDTO dto) {

        return ResponseEntity.ok(
                reservaService.actualizarReserva(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(
            @PathVariable Long id) {

        reservaService.eliminarReserva(id);

        return ResponseEntity.noContent().build();
    }
}
