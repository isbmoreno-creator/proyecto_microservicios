package com.duoc.msvehiculo.controller;


import com.duoc.msvehiculo.dto.CategoriaDTO;
import com.duoc.msvehiculo.dto.CategoriaRequestDTO;
import com.duoc.msvehiculo.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias(){

        return ResponseEntity.ok(categoriaService.obtenerCategorias());
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> guardarCategoria(
            @Valid @RequestBody CategoriaRequestDTO dto){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaService.guardarCategoria(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerCategoriaPorId(
            @PathVariable Long id){

        return ResponseEntity.ok(
                categoriaService.obtenerCategoriaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequestDTO dto){

        return ResponseEntity.ok(
                categoriaService.actualizarCategoria(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(
            @PathVariable Long id){

        categoriaService.eliminarCategoria(id);

        return ResponseEntity.noContent().build();
    }
}
