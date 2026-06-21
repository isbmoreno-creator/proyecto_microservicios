package com.duoc.controller;


import com.duoc.dto.ReservaDTO;
import com.duoc.dto.SucursalesDTO;
import com.duoc.dto.SucursalesRequestDTO;
import com.duoc.servicio.SucursalesServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalesController {
    @Autowired
    private SucursalesServicio sucursalesServicio;

    @GetMapping
    public ResponseEntity<List<SucursalesDTO>> listar(){
        return ResponseEntity.ok(sucursalesServicio.obtenerTodos());
            }
    @GetMapping("/{id}")
    public ResponseEntity<SucursalesDTO> obtener (@PathVariable Integer id){
        SucursalesDTO dto = sucursalesServicio.obtenerPorId(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<SucursalesDTO> guardar(@Valid @RequestBody SucursalesRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalesServicio.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalesDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody SucursalesRequestDTO dto){
    SucursalesDTO actualizado = sucursalesServicio.actualizar(id, dto);
    if (actualizado == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        sucursalesServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/operativas")
    public ResponseEntity<List<SucursalesDTO>> operativas(){
        return ResponseEntity.ok(sucursalesServicio.obtenerOperativas());
    }

    @GetMapping("/{id}/reservas")
    public ResponseEntity<List<ReservaDTO>> reservasPorSucursal(@PathVariable Integer id) {
        return ResponseEntity.ok(sucursalesServicio.obtenerReservasPorSucursal(id));
    }

}
