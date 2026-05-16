package com.duoc.msvehiculo.mapper;

import com.duoc.msvehiculo.dto.CategoriaDTO;
import com.duoc.msvehiculo.dto.CategoriaRequestDTO;
import com.duoc.msvehiculo.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaDTO toDTO(Categoria categoria){

        CategoriaDTO dto = new CategoriaDTO();

        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        dto.setDescripcion(categoria.getDescripcion());
        dto.setActivo(categoria.getActivo());
        dto.setCantidadVehiculos(categoria.getCantidadVehiculos());
        dto.setFechaRegistro(categoria.getFechaRegistro());

        return dto;
    }

    public Categoria toEntity(CategoriaRequestDTO dto){

        Categoria categoria = new Categoria();

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setActivo(dto.getActivo());
        categoria.setCantidadVehiculos(dto.getCantidadVehiculos());
        categoria.setFechaRegistro(dto.getFechaRegistro());

        return categoria;
    }
}
