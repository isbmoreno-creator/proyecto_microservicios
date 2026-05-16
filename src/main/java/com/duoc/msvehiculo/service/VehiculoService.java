package com.duoc.msvehiculo.service;

import com.duoc.msvehiculo.exception.ResourceNotFoundException;
import com.duoc.msvehiculo.dto.VehiculoDTO;
import com.duoc.msvehiculo.dto.VehiculoRequestDTO;
import com.duoc.msvehiculo.mapper.VehiculoMapper;
import com.duoc.msvehiculo.model.Vehiculo;
import com.duoc.msvehiculo.repository.VehiculoRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculoService {

    private static final Logger log =
            LoggerFactory.getLogger(VehiculoService.class);

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private VehiculoMapper vehiculoMapper;

    public List<VehiculoDTO> obtenerVehiculos(){

        log.info("Obteniendo vehiculos");

        List<Vehiculo> vehiculos = vehiculoRepository.findAll();

        List<VehiculoDTO> listaDTO = new ArrayList<>();

        for(Vehiculo vehiculo : vehiculos){
            listaDTO.add(vehiculoMapper.toDTO(vehiculo));
        }

        return listaDTO;
    }

    public VehiculoDTO guardarVehiculo(VehiculoRequestDTO dto){

        log.info("Guardando vehiculo");

        Vehiculo vehiculo = vehiculoMapper.toEntity(dto);

        Vehiculo guardado = vehiculoRepository.save(vehiculo);

        return vehiculoMapper.toDTO(guardado);
    }

    public VehiculoDTO obtenerVehiculoPorId(Long id){

        log.info("Buscando vehiculo por id");

        Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);

        if(vehiculo == null){
            throw new ResourceNotFoundException("Vehiculo no encontrado");
        }

        return vehiculoMapper.toDTO(vehiculo);
    }

    public VehiculoDTO actualizarVehiculo(Long id, VehiculoRequestDTO dto){

        log.info("Actualizando vehiculo");

        Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);

        if(vehiculo == null){
            throw new ResourceNotFoundException("Vehiculo no encontrado");
        }

        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setPrecioDiario(dto.getPrecioDiario());
        vehiculo.setDisponible(dto.getDisponible());
        vehiculo.setFechaIngreso(dto.getFechaIngreso());

        Vehiculo actualizado = vehiculoRepository.save(vehiculo);

        return vehiculoMapper.toDTO(actualizado);
    }

    public void eliminarVehiculo(Long id){

        log.info("Eliminando vehiculo");

        Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);

        if(vehiculo == null){
            throw new ResourceNotFoundException("Vehiculo no encontrado");
        }

        vehiculoRepository.deleteById(id);
    }
}

