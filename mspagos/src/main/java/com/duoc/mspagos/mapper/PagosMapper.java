package com.duoc.mspagos.mapper;

import com.duoc.mspagos.dto.PagosDTO;
import com.duoc.mspagos.dto.PagosRequestDTO;
import com.duoc.mspagos.model.Pagos;
import org.springframework.stereotype.Component;

@Component
public class PagosMapper {

    public PagosDTO toDTO(Pagos pago){

        PagosDTO dto = new PagosDTO();

        dto.setId(pago.getId());
        dto.setMonto(pago.getMonto());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setPagado(pago.getPagado());
        dto.setFechaPago(pago.getFechaPago());
        dto.setNumeroCuotas(pago.getNumeroCuotas());
        dto.setReservaId(pago.getReservaId());

        return dto;
    }

    public Pagos toEntity(PagosRequestDTO dto){

        Pagos pagos = new Pagos();

        pagos.setMonto(dto.getMonto());
        pagos.setMetodoPago(dto.getMetodoPago());
        pagos.setPagado(dto.getPagado());
        pagos.setFechaPago(dto.getFechaPago());
        pagos.setNumeroCuotas(dto.getNumeroCuotas());
        pagos.setReservaId(dto.getReservaId());

        return pagos;
    }
}
