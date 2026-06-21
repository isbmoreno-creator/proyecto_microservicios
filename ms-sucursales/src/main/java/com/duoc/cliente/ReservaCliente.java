package com.duoc.cliente;

import com.duoc.dto.ReservaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-reservas", url = "http://localhost:8083")
public interface ReservaCliente {

    @GetMapping("/api/v1/reserva/sucursal/{sucursalId}")
    List<ReservaDTO> obtenerReservasPorSucursal(@PathVariable Integer sucursalId);
}
