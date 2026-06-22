package com.duoc.msvehiculo.service;

import com.duoc.msvehiculo.dto.VehiculoDTO;
import com.duoc.msvehiculo.dto.VehiculoRequestDTO;
import com.duoc.msvehiculo.exception.ResourceNotFoundException;
import com.duoc.msvehiculo.mapper.VehiculoMapper;
import com.duoc.msvehiculo.model.Vehiculo;
import com.duoc.msvehiculo.repository.VehiculoRepository;
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
class VehiculoServiceTest {

    @Mock
    private VehiculoRepository vehiculoRepository;

    @Mock
    private VehiculoMapper vehiculoMapper;

    @InjectMocks
    private VehiculoService vehiculoService;

    @Test
    void obtenerVehiculosRetornaListaDeVehiculos() {
        Vehiculo vehiculo = vehiculo(1L);
        VehiculoDTO dto = vehiculoDTO(1L);

        when(vehiculoRepository.findAll()).thenReturn(List.of(vehiculo));
        when(vehiculoMapper.toDTO(vehiculo)).thenReturn(dto);

        List<VehiculoDTO> resultado = vehiculoService.obtenerVehiculos();

        assertThat(resultado).containsExactly(dto);
        verify(vehiculoRepository).findAll();
        verify(vehiculoMapper).toDTO(vehiculo);
    }

    @Test
    void guardarVehiculoGuardaYRetornaDto() {
        VehiculoRequestDTO request = vehiculoRequest();
        Vehiculo vehiculo = vehiculo(null);
        Vehiculo guardado = vehiculo(1L);
        VehiculoDTO dto = vehiculoDTO(1L);

        when(vehiculoMapper.toEntity(request)).thenReturn(vehiculo);
        when(vehiculoRepository.save(vehiculo)).thenReturn(guardado);
        when(vehiculoMapper.toDTO(guardado)).thenReturn(dto);

        VehiculoDTO resultado = vehiculoService.guardarVehiculo(request);

        assertThat(resultado).isEqualTo(dto);
        verify(vehiculoMapper).toEntity(request);
        verify(vehiculoRepository).save(vehiculo);
        verify(vehiculoMapper).toDTO(guardado);
    }

    @Test
    void obtenerVehiculoPorIdCuandoExisteRetornaVehiculo() {
        Vehiculo vehiculo = vehiculo(1L);
        VehiculoDTO dto = vehiculoDTO(1L);

        when(vehiculoRepository.findById(1L)).thenReturn(Optional.of(vehiculo));
        when(vehiculoMapper.toDTO(vehiculo)).thenReturn(dto);

        VehiculoDTO resultado = vehiculoService.obtenerVehiculoPorId(1L);

        assertThat(resultado).isEqualTo(dto);
        verify(vehiculoRepository).findById(1L);
        verify(vehiculoMapper).toDTO(vehiculo);
    }

    @Test
    void obtenerVehiculoPorIdCuandoNoExisteLanzaExcepcion() {
        when(vehiculoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> vehiculoService.obtenerVehiculoPorId(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Vehiculo no encontrado");

        verify(vehiculoRepository).findById(99L);
        verifyNoMoreInteractions(vehiculoMapper);
    }

    @Test
    void actualizarVehiculoCuandoExisteActualizaCamposYRetornaDto() {
        Vehiculo vehiculo = vehiculo(1L);
        VehiculoRequestDTO request = vehiculoRequestActualizado();
        VehiculoDTO dto = vehiculoDTOActualizado(1L);

        when(vehiculoRepository.findById(1L)).thenReturn(Optional.of(vehiculo));
        when(vehiculoRepository.save(vehiculo)).thenReturn(vehiculo);
        when(vehiculoMapper.toDTO(vehiculo)).thenReturn(dto);

        VehiculoDTO resultado = vehiculoService.actualizarVehiculo(1L, request);

        assertThat(resultado).isEqualTo(dto);
        assertThat(vehiculo.getMarca()).isEqualTo("Kia");
        assertThat(vehiculo.getModelo()).isEqualTo("Rio");
        assertThat(vehiculo.getAnio()).isEqualTo(2023);
        assertThat(vehiculo.getPrecioDiario()).isEqualTo(35000.0);
        assertThat(vehiculo.getDisponible()).isFalse();
        assertThat(vehiculo.getFechaIngreso()).isEqualTo(LocalDate.of(2024, 2, 20));
        verify(vehiculoRepository).save(vehiculo);
    }

    @Test
    void actualizarVehiculoCuandoNoExisteLanzaExcepcion() {
        when(vehiculoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> vehiculoService.actualizarVehiculo(99L, vehiculoRequest()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Vehiculo no encontrado");

        verify(vehiculoRepository).findById(99L);
        verifyNoMoreInteractions(vehiculoMapper);
    }

    @Test
    void eliminarVehiculoCuandoExisteEliminaVehiculoPorId() {
        Vehiculo vehiculo = vehiculo(1L);
        when(vehiculoRepository.findById(1L)).thenReturn(Optional.of(vehiculo));

        vehiculoService.eliminarVehiculo(1L);

        verify(vehiculoRepository).findById(1L);
        verify(vehiculoRepository).deleteById(1L);
    }

    @Test
    void eliminarVehiculoCuandoNoExisteLanzaExcepcion() {
        when(vehiculoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> vehiculoService.eliminarVehiculo(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Vehiculo no encontrado");

        verify(vehiculoRepository).findById(99L);
        verifyNoMoreInteractions(vehiculoMapper);
    }

    private Vehiculo vehiculo(Long id) {
        return new Vehiculo(id, "Toyota", "Yaris", 2022, 28000.0, true,
                LocalDate.of(2024, 1, 10), null);
    }

    private VehiculoDTO vehiculoDTO(Long id) {
        return new VehiculoDTO(id, "Toyota", "Yaris", 2022, 28000.0, true,
                LocalDate.of(2024, 1, 10), 1L);
    }

    private VehiculoRequestDTO vehiculoRequest() {
        return new VehiculoRequestDTO("Toyota", "Yaris", 2022, 28000.0, true,
                LocalDate.of(2024, 1, 10), 1L);
    }

    private VehiculoRequestDTO vehiculoRequestActualizado() {
        return new VehiculoRequestDTO("Kia", "Rio", 2023, 35000.0, false,
                LocalDate.of(2024, 2, 20), 2L);
    }

    private VehiculoDTO vehiculoDTOActualizado(Long id) {
        return new VehiculoDTO(id, "Kia", "Rio", 2023, 35000.0, false,
                LocalDate.of(2024, 2, 20), 2L);
    }
}
