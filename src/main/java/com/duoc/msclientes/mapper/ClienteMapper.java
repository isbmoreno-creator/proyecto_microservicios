package com.duoc.msclientes.mapper;

import com.duoc.msclientes.dto.ClienteDTO;
import com.duoc.msclientes.dto.ClienteRequestDTO;
import com.duoc.msclientes.model.Cliente;

public class ClienteMapper {

    public static ClienteDTO toDTO(Cliente cliente){
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getRut(),
                cliente.getCorreo(),
                cliente.getTelefono(),
                cliente.getActivo(),
                cliente.getFechaRegistro(),
                cliente.getPuntos()
        );
    }
    public static Cliente toEntity(ClienteRequestDTO dto){
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setRut(dto.getRut());
        cliente.setCorreo(dto.getCorreo());
        cliente.setTelefono(dto.getTelefono());
        cliente.setActivo(dto.getActivo());
        cliente.setFechaRegistro(dto.getFechaRegistro());
        cliente.setPuntos(dto.getPuntos());
        return cliente;
    }
}
