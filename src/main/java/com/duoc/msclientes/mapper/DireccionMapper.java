package com.duoc.msclientes.mapper;

import com.duoc.msclientes.dto.DireccionDTO;
import com.duoc.msclientes.model.Direccion;
import org.springframework.stereotype.Component;
import com.duoc.msclientes.dto.DireccionRequestDTO;

@Component
public class DireccionMapper {

    public DireccionDTO toDTO(Direccion direccion){

        return new DireccionDTO(
                direccion.getId(),
                direccion.getCalle(),
                direccion.getNumero(),
                direccion.getComuna(),
                direccion.getRegion(),
                direccion.getCodigoPostal(),
                direccion.getActiva(),
                direccion.getFechaRegistro()
        );
    }

    public Direccion toEntity(DireccionRequestDTO dto){

        Direccion direccion = new Direccion();

        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setComuna(dto.getComuna());
        direccion.setRegion(dto.getRegion());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        direccion.setActiva(dto.getActiva());
        direccion.setFechaRegistro(dto.getFechaRegistro());

        return direccion;
    }
}
