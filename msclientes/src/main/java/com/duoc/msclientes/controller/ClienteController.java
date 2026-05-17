package com.duoc.msclientes.controller;

import com.duoc.msclientes.dto.ClienteDTO;
import com.duoc.msclientes.dto.ClienteRequestDTO;
import com.duoc.msclientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        return ResponseEntity.ok(clienteService.obtenerClientes());
    }
    @PostMapping
    public ResponseEntity<ClienteDTO> guardarCliente(
            @Valid @RequestBody ClienteRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.guardarCliente(dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerCliente(
            @PathVariable Long id){
        return ResponseEntity.ok(clienteService.obtenerPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO dto){

        return ResponseEntity.ok(clienteService.actualizarCliente(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id){

        clienteService.eliminarCliente(id);

        return ResponseEntity.noContent().build();
    }
}
