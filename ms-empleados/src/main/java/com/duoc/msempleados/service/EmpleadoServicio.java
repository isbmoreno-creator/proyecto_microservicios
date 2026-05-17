package com.duoc.msempleados.service;


import com.duoc.msempleados.dto.EmpleadoDTO;
import com.duoc.msempleados.dto.EmpleadoRequestDTO;
import com.duoc.msempleados.model.EmpleadoModel;
import com.duoc.msempleados.repository.EmpleadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;



@Service
public class EmpleadoServicio {

    private static final Logger log = LoggerFactory.getLogger(EmpleadoServicio.class);

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public EmpleadoDTO obtenerPorId(Integer id) {
        log.info("Obteniendo el id de empleado: ", id) ;
        EmpleadoModel empleado = empleadoRepository.findById(id).orElse(null);
         if (empleado == null){
             log.error("No se encontro el empleado id de empleado: {}", id);
             return null;
         }
         return convertir(empleado);
        }

    public List<EmpleadoDTO> obtenerTodos (){
        log.info("Obteniendo todos los empleados:");
        return empleadoRepository.findAll()
                .stream()
                .map(this::convertir)
                .collect(Collectors.toList());
    }

    public EmpleadoDTO guardar (EmpleadoRequestDTO dto){
        log.info("Guardando nuevo empleado: {}", dto);
        EmpleadoModel empleado = new EmpleadoModel();
        empleado.setNombre(dto.getNombre());
        empleado.setApellido(dto.getApellido());
        empleado.setEmail(dto.getEmail());
        empleado.setCargo(dto.getCargo());
        empleado.setAniosExperiencia(dto.getAniosExperiencia());
        empleado.setActivo(dto.isActivo());
        empleado.setFechaContratacion(dto.getFechaContratacion());
        return convertir(empleadoRepository.save(empleado));

    }

    public EmpleadoDTO actualizar (EmpleadoRequestDTO dto, Integer id){
        log.info("Actualizando nuevo empleado con id: {}", dto);
        EmpleadoModel empleado = empleadoRepository.findById(id).orElse(null);
        if (empleado == null){
            log.error("No se encontro el empleado con id: {}", id);
            return null;
        }
        empleado.setNombre(dto.getNombre());
        empleado.setApellido(dto.getApellido());
        empleado.setEmail(dto.getEmail());
        empleado.setCargo(dto.getCargo());
        empleado.setAniosExperiencia(dto.getAniosExperiencia());
        empleado.setActivo(dto.isActivo());
        empleado.setFechaContratacion(dto.getFechaContratacion());
        return convertir(empleadoRepository.save(empleado));
    }

    public void eliminar (Integer id){
                log.info("Eliminando empleado activo con id: {}", id);
                empleadoRepository.deleteById(id);}

    public List<EmpleadoDTO> obtenerActivosPorAnio(Integer anio){
        log.info("Obteniendo empleados activos del año: {}", anio);
        return empleadoRepository.findEmpleadoActivosPorAnio(anio)
         .stream().map(this::convertir).collect(Collectors.toList());

    }

    private EmpleadoDTO convertir(EmpleadoModel empleado){
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(empleado.getId());
        dto.setNombre(empleado.getNombre());
        dto.setApellido(empleado.getApellido());
        dto.setEmail(empleado.getEmail());
        dto.setCargo(empleado.getCargo());
        dto.setAniosExperiencia(empleado.getAniosExperiencia());
        dto.setActivo(empleado.isActivo());
        dto.setFechaContratacion(empleado.getFechaContratacion());
        return dto;
    }



}
