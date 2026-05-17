package com.duoc.servicio;

import com.duoc.dto.RegionDTO;
import com.duoc.dto.RegionRequestDTO;
import com.duoc.model.RegionModel;
import com.duoc.repository.RegionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionServicio {
    private static final Logger log = LoggerFactory.getLogger(RegionServicio.class);

    @Autowired
    private RegionRepository regionRepository;

    public List<RegionDTO> obtenerTodos(){
        log.info("Obteniendo todas las regiones");
        return regionRepository.findAll()
                .stream().map(this::convertir)
                .collect(Collectors.toList());
    }

    public RegionDTO obtenerPorId(Integer id){
        log.info("Obteniendo el id de la region {}", id);
        RegionModel region = regionRepository.findById(id).orElse(null);
        if (region == null){
            log.error("El id de la region no existe {}", id);
            return null;
        }
        return convertir(region);

    }

    public RegionDTO guardar(RegionRequestDTO dto){
        log.info("Guardando la region");
        RegionModel region = new RegionModel();
        region.setNombre(dto.getNombre());
        region.setCodigo(dto.getCodigo());
        region.setDescripcion(dto.getDescripcion());
        region.setNumeroRegion(dto.getNumeroRegion());
        region.setZonaGeografica(dto.getZonaGeografica());
        region.setActivo(dto.isActivo());
        region.setFechaCreacion(dto.getFechaCreacion());
        return convertir(regionRepository.save(region));

    }

    public RegionDTO actualizar(Integer id, RegionRequestDTO dto){
        log.info("Actualizando la region con id {}", id);
        RegionModel region = regionRepository.findById(id).orElse(null);
        if (region == null){
            log.error("El id de la region no existe {}", id);
            return null;
        }
        region.setNombre(dto.getNombre());
        region.setCodigo(dto.getCodigo());
        region.setDescripcion(dto.getDescripcion());
        region.setNumeroRegion(dto.getNumeroRegion());
        region.setZonaGeografica(dto.getZonaGeografica());
        region.setActivo(dto.isActivo());
        region.setFechaCreacion(dto.getFechaCreacion());
        return convertir(regionRepository.save(region));
    }

    public void eliminar(Integer id){
        log.info("Eliminando la region con id {}", id);
        regionRepository.deleteById(id);
            }
    public RegionDTO convertir(RegionModel region){
        RegionDTO dto = new RegionDTO();
        dto.setId(region.getId());
        dto.setNombre(region.getNombre());
        dto.setCodigo(region.getCodigo());
        dto.setDescripcion(region.getDescripcion());
        dto.setNumeroRegion(region.getNumeroRegion());
        dto.setZonaGeografica(region.getZonaGeografica());
        dto.setActivo(region.isActivo());
        dto.setFechaCreacion(region.getFechaCreacion());
        return dto;

    }









}
