package com.duoc.msreservas.service;

import com.duoc.msreservas.exception.ResourceNotFoundException;
import com.duoc.msreservas.dto.EstadoReservaDTO;
import com.duoc.msreservas.dto.EstadoReservaRequestDTO;
import com.duoc.msreservas.mapper.EstadoReservaMapper;
import com.duoc.msreservas.mapper.ReservaMapper;
import com.duoc.msreservas.model.EstadoReserva;
import com.duoc.msreservas.repository.EstadoReservaRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstadoReservaService {

    private static final Logger log =
            LoggerFactory.getLogger(EstadoReservaService.class);

    @Autowired
    private EstadoReservaRepository estadoReservaRepository;

    @Autowired
    private EstadoReservaMapper estadoReservaMapper;

    public List<EstadoReservaDTO> obtenerEstadosReserva(){

        log.info("Obteniendo estados reserva");

        List<EstadoReserva> estados = estadoReservaRepository.findAll();

        List<EstadoReservaDTO> listaDTO = new ArrayList<>();

        for(EstadoReserva estado : estados){
            listaDTO.add(estadoReservaMapper.toDTO(estado));
        }

        return listaDTO;
    }

    public EstadoReservaDTO guardarEstadoReserva(EstadoReservaRequestDTO dto){

        log.info("Guardando estado reserva");

        EstadoReserva estadoReserva = estadoReservaMapper.toEntity(dto);

        EstadoReserva guardado = estadoReservaRepository.save(estadoReserva);

        return estadoReservaMapper.toDTO(guardado);
    }

    public EstadoReservaDTO obtenerEstadoReservaPorId(Long id){

        log.info("Buscando estado reserva por id");

        EstadoReserva estadoReserva =
                estadoReservaRepository.findById(id).orElse(null);

        if(estadoReserva == null){
            throw new ResourceNotFoundException("Estado reserva no encontrado");
        }

        return estadoReservaMapper.toDTO(estadoReserva);
    }

    public EstadoReservaDTO actualizarEstadoReserva(Long id,
                                                    EstadoReservaRequestDTO dto){

        log.info("Actualizando estado reserva");

        EstadoReserva estadoReserva =
                estadoReservaRepository.findById(id).orElse(null);

        if(estadoReserva == null){
            throw new ResourceNotFoundException("Estado reserva no encontrado");
        }

        estadoReserva.setNombre(dto.getNombre());
        estadoReserva.setDescripcion(dto.getDescripcion());
        estadoReserva.setActivo(dto.getActivo());
        estadoReserva.setPrioridad(dto.getPrioridad());
        estadoReserva.setFechaEstado(dto.getFechaEstado());

        EstadoReserva actualizado =
                estadoReservaRepository.save(estadoReserva);

        return estadoReservaMapper.toDTO(actualizado);
    }

    public void eliminarEstadoReserva(Long id){

        log.info("Eliminando estado reserva");

        EstadoReserva estadoReserva =
                estadoReservaRepository.findById(id).orElse(null);

        if(estadoReserva == null){
            throw new ResourceNotFoundException("Estado reserva no encontrado");
        }

        estadoReservaRepository.delete(estadoReserva);
    }
}

