package com.duoc.msvehiculo.service;

import com.duoc.msvehiculo.dto.CategoriaDTO;
import com.duoc.msvehiculo.dto.CategoriaRequestDTO;
import com.duoc.msvehiculo.exception.ResourceNotFoundException;
import com.duoc.msvehiculo.mapper.CategoriaMapper;
import com.duoc.msvehiculo.model.Categoria;
import com.duoc.msvehiculo.repository.CategoriaRepository;
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
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;

    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    void obtenerCategoriasRetornaListaDeCategorias() {
        Categoria categoria = categoria(1L);
        CategoriaDTO dto = categoriaDTO(1L);

        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));
        when(categoriaMapper.toDTO(categoria)).thenReturn(dto);

        List<CategoriaDTO> resultado = categoriaService.obtenerCategorias();

        assertThat(resultado).containsExactly(dto);
        verify(categoriaRepository).findAll();
        verify(categoriaMapper).toDTO(categoria);
    }

    @Test
    void guardarCategoriaGuardaYRetornaDto() {
        CategoriaRequestDTO request = categoriaRequest();
        Categoria categoria = categoria(null);
        Categoria guardada = categoria(1L);
        CategoriaDTO dto = categoriaDTO(1L);

        when(categoriaMapper.toEntity(request)).thenReturn(categoria);
        when(categoriaRepository.save(categoria)).thenReturn(guardada);
        when(categoriaMapper.toDTO(guardada)).thenReturn(dto);

        CategoriaDTO resultado = categoriaService.guardarCategoria(request);

        assertThat(resultado).isEqualTo(dto);
        verify(categoriaMapper).toEntity(request);
        verify(categoriaRepository).save(categoria);
        verify(categoriaMapper).toDTO(guardada);
    }

    @Test
    void obtenerCategoriaPorIdCuandoExisteRetornaCategoria() {
        Categoria categoria = categoria(1L);
        CategoriaDTO dto = categoriaDTO(1L);

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaMapper.toDTO(categoria)).thenReturn(dto);

        CategoriaDTO resultado = categoriaService.obtenerCategoriaPorId(1L);

        assertThat(resultado).isEqualTo(dto);
        verify(categoriaRepository).findById(1L);
        verify(categoriaMapper).toDTO(categoria);
    }

    @Test
    void obtenerCategoriaPorIdCuandoNoExisteLanzaExcepcion() {
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> categoriaService.obtenerCategoriaPorId(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Categoria no encontrada");

        verify(categoriaRepository).findById(99L);
        verifyNoMoreInteractions(categoriaMapper);
    }

    @Test
    void actualizarCategoriaCuandoExisteActualizaCamposYRetornaDto() {
        Categoria categoria = categoria(1L);
        CategoriaRequestDTO request = categoriaRequestActualizada();
        CategoriaDTO dto = categoriaDTOActualizada(1L);

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        when(categoriaMapper.toDTO(categoria)).thenReturn(dto);

        CategoriaDTO resultado = categoriaService.actualizarCategoria(1L, request);

        assertThat(resultado).isEqualTo(dto);
        assertThat(categoria.getNombre()).isEqualTo("SUV");
        assertThat(categoria.getDescripcion()).isEqualTo("Vehiculos familiares amplios");
        assertThat(categoria.getActivo()).isFalse();
        assertThat(categoria.getCantidadVehiculos()).isEqualTo(8);
        assertThat(categoria.getFechaRegistro()).isEqualTo(LocalDate.of(2024, 2, 20));
        verify(categoriaRepository).save(categoria);
    }

    @Test
    void actualizarCategoriaCuandoNoExisteLanzaExcepcion() {
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> categoriaService.actualizarCategoria(99L, categoriaRequest()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Categoria no encontrada");

        verify(categoriaRepository).findById(99L);
        verifyNoMoreInteractions(categoriaMapper);
    }

    @Test
    void eliminarCategoriaCuandoExisteEliminaCategoriaPorId() {
        Categoria categoria = categoria(1L);
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        categoriaService.eliminarCategoria(1L);

        verify(categoriaRepository).findById(1L);
        verify(categoriaRepository).deleteById(1L);
    }

    @Test
    void eliminarCategoriaCuandoNoExisteLanzaExcepcion() {
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> categoriaService.eliminarCategoria(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Categoria no encontrada");

        verify(categoriaRepository).findById(99L);
        verifyNoMoreInteractions(categoriaMapper);
    }

    private Categoria categoria(Long id) {
        return new Categoria(id, "Sedan", "Vehiculos compactos para ciudad", true,
                4, LocalDate.of(2024, 1, 10), List.of());
    }

    private CategoriaDTO categoriaDTO(Long id) {
        return new CategoriaDTO(id, "Sedan", "Vehiculos compactos para ciudad", true,
                4, LocalDate.of(2024, 1, 10));
    }

    private CategoriaRequestDTO categoriaRequest() {
        return new CategoriaRequestDTO("Sedan", "Vehiculos compactos para ciudad", true,
                4, LocalDate.of(2024, 1, 10));
    }

    private CategoriaRequestDTO categoriaRequestActualizada() {
        return new CategoriaRequestDTO("SUV", "Vehiculos familiares amplios", false,
                8, LocalDate.of(2024, 2, 20));
    }

    private CategoriaDTO categoriaDTOActualizada(Long id) {
        return new CategoriaDTO(id, "SUV", "Vehiculos familiares amplios", false,
                8, LocalDate.of(2024, 2, 20));
    }
}
