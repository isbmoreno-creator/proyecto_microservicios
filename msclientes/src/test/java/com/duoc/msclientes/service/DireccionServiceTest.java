package com.duoc.msclientes.service;

import com.duoc.msclientes.dto.DireccionDTO;
import com.duoc.msclientes.dto.DireccionRequestDTO;
import com.duoc.msclientes.exception.ResourceNotFoundException;
import com.duoc.msclientes.mapper.DireccionMapper;
import com.duoc.msclientes.model.Direccion;
import com.duoc.msclientes.repository.DireccionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DireccionServiceTest {

    @Mock
    private DireccionRepository direccionRepository;

    @Mock
    private DireccionMapper direccionMapper;

    @InjectMocks
    private DireccionService direccionService;

    @Test
    void obtenerDireccionesRetornaListaDeDirecciones() {
        Direccion direccion = direccion(1L);
        DireccionDTO dto = direccionDTO(1L);

        when(direccionRepository.findAll()).thenReturn(List.of(direccion));
        when(direccionMapper.toDTO(direccion)).thenReturn(dto);

        List<DireccionDTO> resultado = direccionService.obtenerDirecciones();

        assertThat(resultado).containsExactly(dto);
        verify(direccionRepository).findAll();
        verify(direccionMapper).toDTO(direccion);
    }

    @Test
    void guardarDireccionGuardaYRetornaDto() {
        DireccionRequestDTO request = direccionRequest();
        Direccion direccion = direccion(null);
        Direccion guardada = direccion(1L);
        DireccionDTO dto = direccionDTO(1L);

        when(direccionMapper.toEntity(request)).thenReturn(direccion);
        when(direccionRepository.save(direccion)).thenReturn(guardada);
        when(direccionMapper.toDTO(guardada)).thenReturn(dto);

        DireccionDTO resultado = direccionService.guardarDireccion(request);

        assertThat(resultado).isEqualTo(dto);
        verify(direccionMapper).toEntity(request);
        verify(direccionRepository).save(direccion);
        verify(direccionMapper).toDTO(guardada);
    }

    @Test
    void obtenerPorIdCuandoExisteRetornaDireccion() {
        Direccion direccion = direccion(1L);
        DireccionDTO dto = direccionDTO(1L);

        when(direccionRepository.findById(1L)).thenReturn(Optional.of(direccion));
        when(direccionMapper.toDTO(direccion)).thenReturn(dto);

        DireccionDTO resultado = direccionService.obtenerPorId(1L);

        assertThat(resultado).isEqualTo(dto);
        verify(direccionRepository).findById(1L);
        verify(direccionMapper).toDTO(direccion);
    }

    @Test
    void obtenerPorIdCuandoNoExisteLanzaExcepcion() {
        when(direccionRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> direccionService.obtenerPorId(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Direccion no encontrada");

        verify(direccionRepository).findById(99L);
        verifyNoMoreInteractions(direccionMapper);
    }

    @Test
    void actualizarDireccionCuandoExisteActualizaCamposYRetornaDto() {
        Direccion direccion = direccion(1L);
        DireccionRequestDTO request = direccionRequestActualizada();
        DireccionDTO dto = direccionDTOActualizada(1L);

        when(direccionRepository.findById(1L)).thenReturn(Optional.of(direccion));
        when(direccionRepository.save(direccion)).thenReturn(direccion);
        when(direccionMapper.toDTO(direccion)).thenReturn(dto);

        DireccionDTO resultado = direccionService.actualizarDireccion(1L, request);

        assertThat(resultado).isEqualTo(dto);
        assertThat(direccion.getCalle()).isEqualTo("Av Siempre Viva");
        assertThat(direccion.getNumero()).isEqualTo("742");
        assertThat(direccion.getComuna()).isEqualTo("Providencia");
        assertThat(direccion.getRegion()).isEqualTo("Metropolitana");
        assertThat(direccion.getCodigoPostal()).isEqualTo(8320000);
        assertThat(direccion.getActiva()).isFalse();
        verify(direccionRepository).save(direccion);
    }

    @Test
    void actualizarDireccionCuandoNoExisteLanzaExcepcion() {
        when(direccionRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> direccionService.actualizarDireccion(99L, direccionRequest()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Direccion no encontrada");

        verify(direccionRepository).findById(99L);
        verifyNoMoreInteractions(direccionMapper);
    }

    @Test
    void eliminarDireccionCuandoExisteEliminaDireccion() {
        Direccion direccion = direccion(1L);
        when(direccionRepository.findById(1L)).thenReturn(Optional.of(direccion));

        direccionService.eliminarDireccion(1L);

        verify(direccionRepository).findById(1L);
        verify(direccionRepository).delete(direccion);
    }

    @Test
    void eliminarDireccionCuandoNoExisteLanzaExcepcion() {
        when(direccionRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> direccionService.eliminarDireccion(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Direccion no encontrada");

        verify(direccionRepository).findById(99L);
        verifyNoMoreInteractions(direccionMapper);
    }

    private Direccion direccion(Long id) {
        return new Direccion(id, "Los Olmos", "123", "Santiago", "Metropolitana",
                7500000, true, LocalDate.of(2024, 1, 10), null);
    }

    private DireccionDTO direccionDTO(Long id) {
        return new DireccionDTO(id, "Los Olmos", "123", "Santiago", "Metropolitana",
                7500000, true, LocalDate.of(2024, 1, 10));
    }

    private DireccionRequestDTO direccionRequest() {
        return new DireccionRequestDTO("Los Olmos", "123", "Santiago", "Metropolitana",
                7500000, true, LocalDate.of(2024, 1, 10));
    }

    private DireccionRequestDTO direccionRequestActualizada() {
        return new DireccionRequestDTO("Av Siempre Viva", "742", "Providencia", "Metropolitana",
                8320000, false, LocalDate.of(2024, 2, 20));
    }

    private DireccionDTO direccionDTOActualizada(Long id) {
        return new DireccionDTO(id, "Av Siempre Viva", "742", "Providencia", "Metropolitana",
                8320000, false, LocalDate.of(2024, 1, 10));
    }
}
