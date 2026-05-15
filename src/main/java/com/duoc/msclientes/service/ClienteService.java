package com.duoc.msclientes.service;
import com.duoc.msclientes.dto.ClienteDTO;
import com.duoc.msclientes.dto.ClienteRequestDTO;
import com.duoc.msclientes.exception.ResourceNotFoundException;
import com.duoc.msclientes.mapper.ClienteMapper;
import com.duoc.msclientes.model.Cliente;
import com.duoc.msclientes.repository.ClienteRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private static final Logger log =
            LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;


    public List<ClienteDTO> obtenerClientes(){

        log.info("Obteniendo clientes");

        List<Cliente> clientes = clienteRepository.findAll();

        List<ClienteDTO> listaDTO = new ArrayList<>();

        for(Cliente cliente : clientes){
            listaDTO.add(clienteMapper.toDTO(cliente));
        }

        return listaDTO;
    }


    public ClienteDTO guardarCliente(ClienteRequestDTO dto){

        log.info("Guardando cliente");

        Cliente cliente = clienteMapper.toEntity(dto);

        Cliente guardado = clienteRepository.save(cliente);

        return clienteMapper.toDTO(guardado);
    }


    public ClienteDTO obtenerPorId(Long id){

        log.info("Buscando cliente con id");

        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if(cliente == null){
            throw new ResourceNotFoundException("Cliente no encontrado");
        }

        return clienteMapper.toDTO(cliente);
    }


    public ClienteDTO actualizarCliente(Long id, ClienteRequestDTO dto){

        log.info("Actualizando cliente con id");

        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if(cliente == null){
            throw new ResourceNotFoundException("Cliente no encontrado");
        }

        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setRut(dto.getRut());
        cliente.setCorreo(dto.getCorreo());
        cliente.setTelefono(dto.getTelefono());
        cliente.setPuntos(dto.getPuntos());
        cliente.setActivo(dto.getActivo());

        Cliente actualizado = clienteRepository.save(cliente);

        return clienteMapper.toDTO(actualizado);
    }


    public void eliminarCliente(Long id){

        log.info("Eliminando cliente con id");

        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if(cliente == null){
            throw new ResourceNotFoundException("Cliente no encontrado");
        }

        clienteRepository.delete(cliente);
    }
}

