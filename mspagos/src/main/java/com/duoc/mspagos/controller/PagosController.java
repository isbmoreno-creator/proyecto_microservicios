package com.duoc.mspagos.controller;


import com.duoc.mspagos.dto.PagosDTO;
import com.duoc.mspagos.dto.PagosRequestDTO;
import com.duoc.mspagos.service.PagosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
public class PagosController {

    private final PagosService pagosService;

    @GetMapping
    public ResponseEntity<List<PagosDTO>> listar() {

        List<PagosDTO> pagos = pagosService.obtenerTodos();

        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagosDTO> buscarPorId(@PathVariable Long id) {

        PagosDTO pago = pagosService.obtenerPorId(id);

        return ResponseEntity.ok(pago);
    }

    @PostMapping
    public ResponseEntity<PagosDTO> guardar(
            @Valid @RequestBody PagosRequestDTO dto) {

        PagosDTO pagoGuardado = pagosService.guardarPago(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pagoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagosDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PagosRequestDTO dto) {

        PagosDTO pagoActualizado =
                pagosService.actualizarPago(id, dto);

        return ResponseEntity.ok(pagoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        pagosService.eliminarPago(id);

        return ResponseEntity.noContent().build();
    }
}
