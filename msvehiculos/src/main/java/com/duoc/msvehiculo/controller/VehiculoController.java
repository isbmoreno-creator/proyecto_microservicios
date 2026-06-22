package com.duoc.msvehiculo.controller;

import com.duoc.msvehiculo.dto.VehiculoDTO;
import com.duoc.msvehiculo.dto.VehiculoRequestDTO;
import com.duoc.msvehiculo.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> listarVehiculos(){

        return ResponseEntity.ok(vehiculoService.obtenerVehiculos());
    }

    @PostMapping
    public ResponseEntity<VehiculoDTO> guardarVehiculo(
            @Valid @RequestBody VehiculoRequestDTO dto){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehiculoService.guardarVehiculo(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> obtenerVehiculoPorId(
            @PathVariable Long id){

        return ResponseEntity.ok(vehiculoService.obtenerVehiculoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoDTO> actualizarVehiculo(
            @PathVariable Long id,
            @Valid @RequestBody VehiculoRequestDTO dto){

        return ResponseEntity.ok(
                vehiculoService.actualizarVehiculo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVehiculo(
            @PathVariable Long id){

        vehiculoService.eliminarVehiculo(id);

        return ResponseEntity.noContent().build();
    }
}
