package com.duoc.msclientes.service;

import com.duoc.msclientes.dto.DireccionDTO;
import com.duoc.msclientes.exception.ResourceNotFoundException;
import com.duoc.msclientes.mapper.DireccionMapper;
import com.duoc.msclientes.model.Direccion;
import com.duoc.msclientes.repository.DireccionRepository;
import com.duoc.msclientes.dto.DireccionRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private DireccionMapper direccionMapper;

    public List<DireccionDTO> obtenerDirecciones(){

        List<Direccion> direcciones = direccionRepository.findAll();

        List<DireccionDTO> listaDTO = new ArrayList<>();

        for(Direccion direccion : direcciones){
            listaDTO.add(direccionMapper.toDTO(direccion));
        }

        return listaDTO;
    }

    public DireccionDTO guardarDireccion( DireccionRequestDTO dto){

        Direccion direccion = direccionMapper.toEntity(dto);

        Direccion guardada = direccionRepository.save(direccion);

        return direccionMapper.toDTO(guardada);
    }

    public DireccionDTO obtenerPorId(Long id){

        Direccion direccion = direccionRepository.findById(id).orElse(null);

        if(direccion == null){
            throw new ResourceNotFoundException("Direccion no encontrada");
        }

        return direccionMapper.toDTO(direccion);
    }

    public DireccionDTO actualizarDireccion(Long id, DireccionRequestDTO dto){

        Direccion direccion = direccionRepository.findById(id).orElse(null);

        if(direccion == null){
            throw new ResourceNotFoundException("Direccion no encontrada");
        }

        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setComuna(dto.getComuna());
        direccion.setRegion(dto.getRegion());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        direccion.setActiva(dto.getActiva());

        Direccion actualizada = direccionRepository.save(direccion);

        return direccionMapper.toDTO(actualizada);
    }

    public void eliminarDireccion(Long id){

        Direccion direccion = direccionRepository.findById(id).orElse(null);

        if(direccion == null){
            throw new ResourceNotFoundException("Direccion no encontrada");
        }

        direccionRepository.delete(direccion);
    }
}
