package com.duoc.msreservas.service;

import com.duoc.msreservas.exception.ResourceNotFoundException;
import com.duoc.msreservas.dto.ReservaDTO;
import com.duoc.msreservas.dto.ReservaRequestDTO;
import com.duoc.msreservas.mapper.ReservaMapper;
import com.duoc.msreservas.model.Reserva;
import com.duoc.msreservas.repository.ReservaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {

    private static final Logger log =
            LoggerFactory.getLogger(ReservaService.class);

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaMapper reservaMapper;

    public List<ReservaDTO> obtenerReservas(){

        log.info("Obteniendo reservas");

        List<Reserva> reservas = reservaRepository.findAll();

        List<ReservaDTO> listaDTO = new ArrayList<>();

        for(Reserva reserva : reservas){
            listaDTO.add(reservaMapper.toDTO(reserva));
        }

        return listaDTO;
    }

    public ReservaDTO guardarReserva(ReservaRequestDTO dto){

        log.info("Guardando reserva");

        Reserva reserva = reservaMapper.toEntity(dto);

        Reserva guardada = reservaRepository.save(reserva);

        return reservaMapper.toDTO(guardada);
    }

    public ReservaDTO obtenerReservaPorId(Long id){

        log.info("Buscando reserva por id");

        Reserva reserva = reservaRepository.findById(id).orElse(null);

        if(reserva == null){
            throw new ResourceNotFoundException("Reserva no encontrada");
        }

        return reservaMapper.toDTO(reserva);
    }

    public ReservaDTO actualizarReserva(Long id, ReservaRequestDTO dto){

        log.info("Actualizando reserva");

        Reserva reserva = reservaRepository.findById(id).orElse(null);

        if(reserva == null){
            throw new ResourceNotFoundException("Reserva no encontrada");
        }

        reserva.setFechaReserva(dto.getFechaReserva());
        reserva.setFechaEntrega(dto.getFechaEntrega());
        reserva.setTotal(dto.getTotal());
        reserva.setActiva(dto.getActiva());
        reserva.setCantidadDias(dto.getCantidadDias());

        Reserva actualizada = reservaRepository.save(reserva);

        return reservaMapper.toDTO(actualizada);
    }

    public void eliminarReserva(Long id){

        log.info("Eliminando reserva");

        Reserva reserva = reservaRepository.findById(id).orElse(null);

        if(reserva == null){
            throw new ResourceNotFoundException("Reserva no encontrada");
        }

        reservaRepository.delete(reserva);
    }
}
