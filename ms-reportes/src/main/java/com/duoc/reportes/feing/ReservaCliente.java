package com.duoc.reportes.feing;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ms-reserva", url = "http://localhost:8083")
public interface ReservaCliente {
    @GetMapping("/api/v1/reserva")
    List<Object> obtenerReserva();
}
