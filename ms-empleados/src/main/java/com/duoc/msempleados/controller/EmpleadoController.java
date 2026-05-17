package com.duoc.msempleados.controller;


import com.duoc.msempleados.dto.EmpleadoDTO;
import com.duoc.msempleados.dto.EmpleadoRequestDTO;
import com.duoc.msempleados.service.EmpleadoServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {


 @Autowired
 private EmpleadoServicio empleadoServicio;

 @GetMapping
 public ResponseEntity<List<EmpleadoDTO>>  listar() {
 return ResponseEntity.ok(empleadoServicio.obtenerTodos());
  }

 @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO>  obtenerEmpleado(@PathVariable Integer id) {
     EmpleadoDTO dto = empleadoServicio.obtenerPorId(id);
     if (dto == null) return ResponseEntity.notFound().build();
     return ResponseEntity.ok(dto);

 }

 @PostMapping
    public ResponseEntity<EmpleadoDTO>  guardarEmpleado(@RequestBody EmpleadoRequestDTO dto) {
     return ResponseEntity.status(HttpStatus.CREATED).body(empleadoServicio.guardar(dto));
      }

      @PutMapping("/{id}")
      public ResponseEntity<EmpleadoDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody EmpleadoRequestDTO dto){
          EmpleadoDTO actualizado = empleadoServicio.actualizar(dto, id);
          if (actualizado == null) return ResponseEntity.notFound().build();
          return ResponseEntity.ok(actualizado);
      }

      @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminar(@PathVariable Integer id){
         empleadoServicio.eliminar(id);
         return ResponseEntity.noContent().build();
      }

      @GetMapping("/activos/{anio}")
    public ResponseEntity <List<EmpleadoDTO>>  activosPorAnio(@PathVariable Integer anio){
     return ResponseEntity.ok(empleadoServicio.obtenerActivosPorAnio(anio));
      }

}
