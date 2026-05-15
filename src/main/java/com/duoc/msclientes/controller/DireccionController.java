package com.duoc.msclientes.controller;


import com.duoc.msclientes.dto.DireccionDTO;
import com.duoc.msclientes.dto.DireccionRequestDTO;
import com.duoc.msclientes.service.DireccionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direcciones")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @GetMapping
    public ResponseEntity<List<DireccionDTO>> listarDirecciones(){

        return ResponseEntity.ok(direccionService.obtenerDirecciones());
    }
    @PostMapping
    public ResponseEntity<DireccionDTO> guardarDireccion(
            @Valid @RequestBody DireccionRequestDTO dto){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(direccionService.guardarDireccion(dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DireccionDTO> obtenerDireccion(
            @PathVariable Long id){

        return ResponseEntity.ok(direccionService.obtenerPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DireccionDTO> actualizarDireccion(
            @PathVariable Long id,
            @Valid @RequestBody DireccionRequestDTO dto){

        return ResponseEntity.ok(
                direccionService.actualizarDireccion(id, dto)
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDireccion(
            @PathVariable Long id){

        direccionService.eliminarDireccion(id);

        return ResponseEntity.ok("Direccion eliminada correctamente");
    }
}
