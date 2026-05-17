package com.duoc.msreservas.mapper;

import com.duoc.msreservas.dto.EstadoReservaDTO;
import com.duoc.msreservas.dto.EstadoReservaRequestDTO;
import com.duoc.msreservas.dto.ReservaDTO;
import com.duoc.msreservas.dto.ReservaRequestDTO;
import com.duoc.msreservas.model.EstadoReserva;
import com.duoc.msreservas.model.Reserva;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservaMapper {

    public ReservaDTO toDTO(Reserva reserva){

        ReservaDTO dto = new ReservaDTO();

        dto.setId(reserva.getId());
        dto.setFechaReserva(reserva.getFechaReserva());
        dto.setFechaEntrega(reserva.getFechaEntrega());
        dto.setTotal(reserva.getTotal());
        dto.setActiva(reserva.getActiva());
        dto.setCantidadDias(reserva.getCantidadDias());

        if(reserva.getEstadoReserva() != null){
            dto.setEstadoReservaId(reserva.getEstadoReserva().getId());
        }    return dto;
    }

    public Reserva toEntity(ReservaRequestDTO dto){

        Reserva reserva = new Reserva();

        reserva.setFechaReserva(dto.getFechaReserva());
        reserva.setFechaEntrega(dto.getFechaEntrega());
        reserva.setTotal(dto.getTotal());
        reserva.setActiva(dto.getActiva());
        reserva.setCantidadDias(dto.getCantidadDias());

        return reserva;
    }
}
