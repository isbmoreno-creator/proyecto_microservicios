package com.duoc.msvehiculo.mapper;

import com.duoc.msvehiculo.dto.VehiculoDTO;
import com.duoc.msvehiculo.dto.VehiculoRequestDTO;
import com.duoc.msvehiculo.model.Vehiculo;
import org.springframework.stereotype.Component;

@Component
public class VehiculoMapper {

    public VehiculoDTO toDTO(Vehiculo vehiculo){

        VehiculoDTO dto = new VehiculoDTO();

        dto.setId(vehiculo.getId());
        dto.setMarca(vehiculo.getMarca());
        dto.setModelo(vehiculo.getModelo());
        dto.setAnio(vehiculo.getAnio());
        dto.setPrecioDiario(vehiculo.getPrecioDiario());
        dto.setDisponible(vehiculo.getDisponible());
        dto.setFechaIngreso(vehiculo.getFechaIngreso());

        if(vehiculo.getCategoria() != null){
            dto.setCategoriaId(vehiculo.getCategoria().getId());
        }

        return dto;
    }

    public Vehiculo toEntity(VehiculoRequestDTO dto){

        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setPrecioDiario(dto.getPrecioDiario());
        vehiculo.setDisponible(dto.getDisponible());
        vehiculo.setFechaIngreso(dto.getFechaIngreso());

        return vehiculo;
    }
}
