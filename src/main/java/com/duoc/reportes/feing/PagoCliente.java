package com.duoc.reportes.feing;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ms-pagos", url = "http://localhost:8084")
public interface PagoCliente {

    @GetMapping("/api/v1/pagos")
    List<Object> obtenerPagos();

}
