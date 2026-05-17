package com.duoc.servicio;


import com.duoc.dto.SucursalesDTO;
import com.duoc.dto.SucursalesRequestDTO;
import com.duoc.model.RegionModel;
import com.duoc.model.SucursalesModel;
import com.duoc.repository.RegionRepository;
import com.duoc.repository.SucursalesRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SucursalesServicio {

    private static final Logger log = LoggerFactory.getLogger(SucursalesServicio.class);

    @Autowired
    private SucursalesRepository sucursalesRepository;

    @Autowired
    private RegionRepository regionRepository;

    public List<SucursalesDTO> obtenerTodos() {
        log.info("Obteniendo todos los Sucursales");
        return sucursalesRepository.findAll().
                stream().map(this::convertir)
                .collect(Collectors.toList());

    }
    public SucursalesDTO obtenerPorId(Integer id) {
        log.info("Obteniendo por Sucursal con id: " + id);
        SucursalesModel sucursales = sucursalesRepository.findById(id).orElse(null);
        if (sucursales == null) {
            log.error("No se encontro el Sucursal con id: " + id);
            return null;
        }
        return convertir(sucursales);
    }

    public SucursalesDTO guardar(SucursalesRequestDTO dto) {
        log.info("Nueva sucursal guardada");
        SucursalesModel sucursales = new SucursalesModel();
        sucursales.setNombre(dto.getNombre());
        sucursales.setDescripcion(dto.getDescripcion());
        sucursales.setNumero(String.valueOf(dto.getNumero()));
        sucursales.setCapacidad(dto.getCapacidad());
        sucursales.setCostoArriendo(dto.getCostoArriendo());
        sucursales.setOperativa(dto.isActivo());
        sucursales.setFechaApertura(dto.getFechaApertura());

        RegionModel region = regionRepository.findById(dto.getRegionId()).orElse(null);
        sucursales.setRegion(region);
        return convertir(sucursalesRepository.save(sucursales));
    }

    public void eliminar(Integer id) {
        log.info("Eliminando Sucursal con id: " + id);
      sucursalesRepository.deleteById(id);
    }

    public SucursalesDTO actualizar(Integer id, SucursalesRequestDTO dto) {
        log.info("Actualizando sucursal con id: {}", id);
        SucursalesModel sucursales = sucursalesRepository.findById(id).orElse(null);
        if (sucursales == null) {
            log.error("No se encontro la sucursal con id: {}", id);
            return null;
        }
        sucursales.setNombre(dto.getNombre());
        sucursales.setDescripcion(dto.getDescripcion());
        sucursales.setNumero(String.valueOf(dto.getNumero()));
        sucursales.setCapacidad(dto.getCapacidad());
        sucursales.setCostoArriendo(dto.getCostoArriendo());
        sucursales.setOperativa(dto.isActivo());
        sucursales.setFechaApertura(dto.getFechaApertura());
        RegionModel region = regionRepository.findById(dto.getRegionId()).orElse(null);
        sucursales.setRegion(region);
        return convertir(sucursalesRepository.save(sucursales));
    }
        public List<SucursalesDTO> obtenerOperativas() {
            log.info("Obteniendo sucursales operativas");
            return sucursalesRepository.findSucursalesOperativasOrdenadas()
                    .stream().map(this::convertir)
                    .collect(Collectors.toList());
        }


    public SucursalesDTO convertir(SucursalesModel sucursales) {
        SucursalesDTO dto = new SucursalesDTO();
        dto.setId(sucursales.getId());
        dto.setNombre(sucursales.getNombre());
        dto.setDescripcion(sucursales.getDescripcion());
        dto.setNumero(sucursales.getNumero());
        dto.setCapacidad(sucursales.getCapacidad());
        dto.setCostoArriendo(sucursales.getCostoArriendo());
        dto.setOperativa(sucursales.isOperativa());
        dto.setFechaApertura(sucursales.getFechaApertura());
        if (sucursales.getRegion() != null) {
            dto.setRegionId(sucursales.getRegion().getId());
        }
        return dto;
    }
}
