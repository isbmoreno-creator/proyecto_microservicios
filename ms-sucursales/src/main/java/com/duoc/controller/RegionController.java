package com.duoc.controller;


import com.duoc.dto.RegionDTO;
import com.duoc.dto.RegionRequestDTO;
import com.duoc.repository.RegionRepository;
import com.duoc.servicio.RegionServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.Region;
import java.util.List;

@RestController
@RequestMapping("/api/v1/regiones")
public class RegionController {

    @Autowired
    private RegionServicio regionServicio;

    @GetMapping
    public ResponseEntity<List<RegionDTO>> listar() {
    return ResponseEntity.ok(regionServicio.obtenerTodos());
}
    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> obtener(@PathVariable Integer id) {
    RegionDTO dto = regionServicio.obtenerPorId(id);
    if (dto == null) {return ResponseEntity.notFound().build();}
    return ResponseEntity.ok(dto);}

    @PostMapping
    public ResponseEntity<RegionDTO> guardar(@Valid @RequestBody RegionRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(regionServicio.guardar(dto));

}
    @PutMapping("/{id}")
    public ResponseEntity<RegionDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody RegionRequestDTO dto) {
    RegionDTO actualizado  = regionServicio.actualizar(id, dto);
    if (actualizado == null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(actualizado);}

    @DeleteMapping("/{id}")
    public ResponseEntity<RegionDTO> eliminar(@PathVariable Integer id) {
    regionServicio.eliminar(id);
    return ResponseEntity.noContent().build();
    }
}
