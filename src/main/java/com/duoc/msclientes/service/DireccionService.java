package com.duoc.msclientes.service;

import com.duoc.msclientes.model.Direccion;
import com.duoc.msclientes.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    public List<Direccion> obtenerDirecciones(){
        return direccionRepository.findAll();
    }
    public Direccion guardarDireccion(Direccion direccion){
        return direccionRepository.save(direccion);
    }
}
