package com.duoc.msvehiculo.service;

import com.duoc.msvehiculo.exception.ResourceNotFoundException;
import com.duoc.msvehiculo.dto.CategoriaDTO;
import com.duoc.msvehiculo.dto.CategoriaRequestDTO;
import com.duoc.msvehiculo.mapper.CategoriaMapper;
import com.duoc.msvehiculo.model.Categoria;
import com.duoc.msvehiculo.repository.CategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    private static final Logger log =
            LoggerFactory.getLogger(CategoriaService.class);

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    public List<CategoriaDTO> obtenerCategorias(){

        log.info("Obteniendo categorias");

        List<Categoria> categorias = categoriaRepository.findAll();

        List<CategoriaDTO> listaDTO = new ArrayList<>();

        for(Categoria categoria : categorias){
            listaDTO.add(categoriaMapper.toDTO(categoria));
        }

        return listaDTO;
    }

    public CategoriaDTO guardarCategoria(CategoriaRequestDTO dto){

        log.info("Guardando categoria");

        Categoria categoria = categoriaMapper.toEntity(dto);

        Categoria guardada = categoriaRepository.save(categoria);

        return categoriaMapper.toDTO(guardada);
    }

    public CategoriaDTO obtenerCategoriaPorId(Long id){

        log.info("Buscando categoria por id");

        Categoria categoria = categoriaRepository.findById(id).orElse(null);

        if(categoria == null){
            throw new ResourceNotFoundException("Categoria no encontrada");
        }

        return categoriaMapper.toDTO(categoria);
    }

    public CategoriaDTO actualizarCategoria(Long id, CategoriaRequestDTO dto){

        log.info("Actualizando categoria");

        Categoria categoria = categoriaRepository.findById(id).orElse(null);

        if(categoria == null){
            throw new ResourceNotFoundException("Categoria no encontrada");
        }

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setActivo(dto.getActivo());
        categoria.setCantidadVehiculos(dto.getCantidadVehiculos());
        categoria.setFechaRegistro(dto.getFechaRegistro());

        Categoria actualizada = categoriaRepository.save(categoria);

        return categoriaMapper.toDTO(actualizada);
    }

    public void eliminarCategoria(Long id){

        log.info("Eliminando categoria");

        Categoria categoria = categoriaRepository.findById(id).orElse(null);

        if(categoria == null){
            throw new ResourceNotFoundException("Categoria no encontrada");
        }

        categoriaRepository.deleteById(id);
    }
}
