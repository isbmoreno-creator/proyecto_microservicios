package com.duoc.msreservas.mapper;

import com.duoc.msreservas.dto.EstadoReservaDTO;
import com.duoc.msreservas.dto.EstadoReservaRequestDTO;
import com.duoc.msreservas.model.EstadoReserva;
import org.springframework.stereotype.Component;

@Component
public class EstadoReservaMapper {

    public EstadoReservaDTO toDTO(EstadoReserva estadoReserva){

        EstadoReservaDTO dto = new EstadoReservaDTO();

        dto.setId(estadoReserva.getId());
        dto.setNombre(estadoReserva.getNombre());
        dto.setDescripcion(estadoReserva.getDescripcion());
        dto.setActivo(estadoReserva.getActivo());
        dto.setPrioridad(estadoReserva.getPrioridad());
        dto.setFechaEstado(estadoReserva.getFechaEstado());

        return dto;
    }

    public EstadoReserva toEntity(EstadoReservaRequestDTO dto){

        EstadoReserva estadoReserva = new EstadoReserva();

        estadoReserva.setNombre(dto.getNombre());
        estadoReserva.setDescripcion(dto.getDescripcion());
        estadoReserva.setActivo(dto.getActivo());
        estadoReserva.setPrioridad(dto.getPrioridad());
        estadoReserva.setFechaEstado(dto.getFechaEstado());

        return estadoReserva;
    }
}
