package com.duoc.msreservas.service;

import com.duoc.msreservas.dto.ReservaRequestDTO;
import com.duoc.msreservas.mapper.ReservaMapper;
import com.duoc.msreservas.dto.ReservaDTO;
import com.duoc.msreservas.model.EstadoReserva;
import com.duoc.msreservas.model.Reserva;
import com.duoc.msreservas.repository.EstadoReservaRepository;
import com.duoc.msreservas.repository.ReservaRepository;
import com.duoc.msreservas.exception.ResourceNotFoundException;

import com.duoc.msreservas.service.ReservaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {

@Mock
private ReservaRepository reservaRepository;

@Mock
private EstadoReservaRepository estadoReservaRepository;

@Mock
private ReservaMapper reservaMapper;

@InjectMocks
private ReservaService reservaService;


@Test
    void obtenerReservaPorId_CuandoExiste_DeberiaRetornarReservaDTO() {
        Long reservaId = 1L;
        Reserva reservaMock = new Reserva();
        reservaMock.setId(reservaId);

        ReservaDTO reservaDTOMock = new ReservaDTO();
        reservaDTOMock.setId(reservaId);

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reservaMock));
        when(reservaMapper.toDTO(reservaMock)).thenReturn(reservaDTOMock);


        ReservaDTO resultado = reservaService.obtenerReservaPorId(reservaId);


        assertNotNull(resultado, "El resultado no debería ser nulo");
        assertEquals(reservaId, resultado.getId(), "El ID de la reserva debería coincidir");


        verify(reservaRepository, times(1)).findById(reservaId);
        verify(reservaMapper, times(1)).toDTO(reservaMock);
    }

@Test
    void obtenerReservaPorId_CuandoNoExiste_DeberiaLanzarResourceNotFoundException() {

        Long reservaId = 99L;
        when(reservaRepository.findById(reservaId)).thenReturn(Optional.empty());


        assertThrows(ResourceNotFoundException.class, () -> {
            reservaService.obtenerReservaPorId(reservaId);
        }, "Debería lanzar ResourceNotFoundException cuando la reserva no existe");

        verify(reservaRepository, times(1)).findById(reservaId);
        verifyNoInteractions(reservaMapper);
    }
@Test
    void obtenerReservas_DeberiaRetornarListaDeReservas() {
        Reserva reservaMock = new Reserva();
        reservaMock.setId(1L);

        ReservaDTO reservaDTOMock = new ReservaDTO();
        reservaDTOMock.setId(1L);

        when(reservaRepository.findAll()).thenReturn(List.of(reservaMock));
        when(reservaMapper.toDTO(reservaMock)).thenReturn(reservaDTOMock);

        List<ReservaDTO> resultado = reservaService.obtenerReservas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(reservaRepository, times(1)).findAll();
    }

@Test
    void guardarReserva_DeberiaRetornarDTOCreada() {
        ReservaRequestDTO requestDTO = new ReservaRequestDTO();
        requestDTO.setFechaReserva(LocalDate.of(2026,06,20));
        requestDTO.setFechaEntrega(LocalDate.of(2026,06,25));
        requestDTO.setTotal(50000.00);
        requestDTO.setActiva(true);
        requestDTO.setCantidadDias(5);
        requestDTO.setSucursalId(1);
        requestDTO.setEstadoReservaId(1L);


        EstadoReserva estadoMock = new EstadoReserva();
        estadoMock.setId(1L);

        Reserva reservaSinGuardar = new Reserva();
        Reserva reservaGuardada = new Reserva();
        reservaGuardada.setId(1L);

        ReservaDTO reservaDTOMock = new ReservaDTO();
        reservaDTOMock.setId(1L);

        when(reservaMapper.toEntity(requestDTO)).thenReturn(reservaSinGuardar);
        when(estadoReservaRepository.findById(1L)).thenReturn(Optional.of(estadoMock));
        when(reservaRepository.save(reservaSinGuardar)).thenReturn(reservaGuardada);
        when(reservaMapper.toDTO(reservaGuardada)).thenReturn(reservaDTOMock);

        ReservaDTO resultado = reservaService.guardarReserva(requestDTO);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(reservaRepository, times(1)).save(reservaSinGuardar);
}
@Test
    void actualizarReserva_CuandoExiste_DeberiaRetornarReservaActualizada() {
        Long reservaId = 1L;

        ReservaRequestDTO requestDTO = new ReservaRequestDTO();
        requestDTO.setFechaReserva(LocalDate.of(2026, 7, 1));
        requestDTO.setFechaEntrega(LocalDate.of(2026, 7, 5));
        requestDTO.setTotal(60000.00);
        requestDTO.setActiva(true);
        requestDTO.setCantidadDias(4);
        requestDTO.setSucursalId(2);
        requestDTO.setEstadoReservaId(1L);

        Reserva reservaExistente = new Reserva();
        reservaExistente.setId(reservaId);

        EstadoReserva estadoMock = new EstadoReserva();
        estadoMock.setId(1L);

        Reserva reservaActualizada = new Reserva();
        reservaActualizada.setId(reservaId);

        ReservaDTO reservaDTOMock = new ReservaDTO();
        reservaDTOMock.setId(reservaId);

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reservaExistente));
        when(estadoReservaRepository.findById(1L)).thenReturn(Optional.of(estadoMock));
        when(reservaRepository.save(reservaExistente)).thenReturn(reservaActualizada);
        when(reservaMapper.toDTO(reservaActualizada)).thenReturn(reservaDTOMock);

        ReservaDTO resultado = reservaService.actualizarReserva(reservaId, requestDTO);

        assertNotNull(resultado);
        assertEquals(reservaId, resultado.getId());
        verify(reservaRepository, times(1)).save(reservaExistente);
    }

@Test
    void actualizarReserva_CuandoNoExiste_DeberiaLanzarResourceNotFoundException() {
        Long reservaId = 99L;
        ReservaRequestDTO requestDTO = new ReservaRequestDTO();

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            reservaService.actualizarReserva(reservaId, requestDTO);
        });

        verify(reservaRepository, times(1)).findById(reservaId);
        verify(reservaRepository, never()).save(any());
    }
@Test
    void eliminarReserva_CuandoExiste_DeberiaEliminarReserva() {
        Long reservaId = 1L;
        Reserva reservaExistente = new Reserva();
        reservaExistente.setId(reservaId);

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reservaExistente));

        reservaService.eliminarReserva(reservaId);

        verify(reservaRepository, times(1)).findById(reservaId);
        verify(reservaRepository, times(1)).delete(reservaExistente);
    }
@Test
    void eliminarReserva_CuandoNoExiste_DeberiaLanzarResourceNotFoundException() {
        Long reservaId = 99L;

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            reservaService.eliminarReserva(reservaId);
        });

        verify(reservaRepository, times(1)).findById(reservaId);
        verify(reservaRepository, never()).delete(any());
    }

}
