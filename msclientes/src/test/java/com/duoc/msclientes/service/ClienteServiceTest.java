package com.duoc.msclientes.service;

import com.duoc.msclientes.dto.ClienteDTO;
import com.duoc.msclientes.dto.ClienteRequestDTO;
import com.duoc.msclientes.exception.ResourceNotFoundException;
import com.duoc.msclientes.mapper.ClienteMapper;
import com.duoc.msclientes.model.Cliente;
import com.duoc.msclientes.repository.ClienteRepository;
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
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void obtenerClientesRetornaListaDeClientes() {
        Cliente cliente = cliente(1L);
        ClienteDTO dto = clienteDTO(1L);

        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        when(clienteMapper.toDTO(cliente)).thenReturn(dto);

        List<ClienteDTO> resultado = clienteService.obtenerClientes();

        assertThat(resultado).containsExactly(dto);
        verify(clienteRepository).findAll();
        verify(clienteMapper).toDTO(cliente);
    }

    @Test
    void guardarClienteGuardaYRetornaDto() {
        ClienteRequestDTO request = clienteRequest();
        Cliente cliente = cliente(null);
        Cliente guardado = cliente(1L);
        ClienteDTO dto = clienteDTO(1L);

        when(clienteMapper.toEntity(request)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(guardado);
        when(clienteMapper.toDTO(guardado)).thenReturn(dto);

        ClienteDTO resultado = clienteService.guardarCliente(request);

        assertThat(resultado).isEqualTo(dto);
        verify(clienteMapper).toEntity(request);
        verify(clienteRepository).save(cliente);
        verify(clienteMapper).toDTO(guardado);
    }

    @Test
    void obtenerPorIdCuandoExisteRetornaCliente() {
        Cliente cliente = cliente(1L);
        ClienteDTO dto = clienteDTO(1L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteMapper.toDTO(cliente)).thenReturn(dto);

        ClienteDTO resultado = clienteService.obtenerPorId(1L);

        assertThat(resultado).isEqualTo(dto);
        verify(clienteRepository).findById(1L);
        verify(clienteMapper).toDTO(cliente);
    }

    @Test
    void obtenerPorIdCuandoNoExisteLanzaExcepcion() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clienteService.obtenerPorId(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Cliente no encontrado");

        verify(clienteRepository).findById(99L);
        verifyNoMoreInteractions(clienteMapper);
    }

    @Test
    void actualizarClienteCuandoExisteActualizaCamposYRetornaDto() {
        Cliente cliente = cliente(1L);
        ClienteRequestDTO request = clienteRequestActualizado();
        ClienteDTO dto = clienteDTOActualizado(1L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.toDTO(cliente)).thenReturn(dto);

        ClienteDTO resultado = clienteService.actualizarCliente(1L, request);

        assertThat(resultado).isEqualTo(dto);
        assertThat(cliente.getNombre()).isEqualTo("Ana");
        assertThat(cliente.getApellido()).isEqualTo("Perez");
        assertThat(cliente.getRut()).isEqualTo("11222333-4");
        assertThat(cliente.getCorreo()).isEqualTo("ana.perez@test.cl");
        assertThat(cliente.getTelefono()).isEqualTo("987654321");
        assertThat(cliente.getPuntos()).isEqualTo(50);
        assertThat(cliente.getActivo()).isFalse();
        verify(clienteRepository).save(cliente);
    }

    @Test
    void actualizarClienteCuandoNoExisteLanzaExcepcion() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clienteService.actualizarCliente(99L, clienteRequest()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Cliente no encontrado");

        verify(clienteRepository).findById(99L);
        verifyNoMoreInteractions(clienteMapper);
    }

    @Test
    void eliminarClienteCuandoExisteEliminaCliente() {
        Cliente cliente = cliente(1L);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        clienteService.eliminarCliente(1L);

        verify(clienteRepository).findById(1L);
        verify(clienteRepository).delete(cliente);
    }

    @Test
    void eliminarClienteCuandoNoExisteLanzaExcepcion() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clienteService.eliminarCliente(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Cliente no encontrado");

        verify(clienteRepository).findById(99L);
        verifyNoMoreInteractions(clienteMapper);
    }

    private Cliente cliente(Long id) {
        return new Cliente(id, "Juan", "Soto", "12345678-9", "juan@test.cl",
                "912345678", 10, true, LocalDate.of(2024, 1, 10), List.of());
    }

    private ClienteDTO clienteDTO(Long id) {
        return new ClienteDTO(id, "Juan", "Soto", "12345678-9", "juan@test.cl",
                "912345678", 10, true, LocalDate.of(2024, 1, 10));
    }

    private ClienteRequestDTO clienteRequest() {
        return new ClienteRequestDTO("Juan", "Soto", "12345678-9", "juan@test.cl",
                "912345678", 10, true, LocalDate.of(2024, 1, 10));
    }

    private ClienteRequestDTO clienteRequestActualizado() {
        return new ClienteRequestDTO("Ana", "Perez", "11222333-4", "ana.perez@test.cl",
                "987654321", 50, false, LocalDate.of(2024, 2, 20));
    }

    private ClienteDTO clienteDTOActualizado(Long id) {
        return new ClienteDTO(id, "Ana", "Perez", "11222333-4", "ana.perez@test.cl",
                "987654321", 50, false, LocalDate.of(2024, 1, 10));
    }
}
