package com.duoc.msempleados.runner;


import com.duoc.msempleados.model.EmpleadoModel;
import com.duoc.msempleados.repository.EmpleadoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private EmpleadoRepository  empleadoRepository;

    @Override
    public void run(String... args) throws Exception{
        if (empleadoRepository.count() == 0){
            log.info("Estamos cargando los datos del empleado...");

            EmpleadoModel e1 = new EmpleadoModel();
            e1.setNombre("Ana");
            e1.setApellido("González");
            e1.setEmail("ana.gonzalez@tuarriendo.cl");
            e1.setCargo("Vendedor");
            e1.setAniosExperiencia(3);
            e1.setSalario(850000.0);
            e1.setActivo(true);
            e1.setFechaContratacion(LocalDate.of(2021, 3, 15));

            EmpleadoModel e2 = new EmpleadoModel();
            e2.setNombre("Carlos");
            e2.setApellido("Pérez");
            e2.setEmail("carlos.perez@tuarriendo.cl");
            e2.setCargo("Gerente");
            e2.setAniosExperiencia(8);
            e2.setSalario(1500000.0);
            e2.setActivo(true);
            e2.setFechaContratacion(LocalDate.of(2019, 6, 1));

            EmpleadoModel e3 = new EmpleadoModel();
            e3.setNombre("María");
            e3.setApellido("López");
            e3.setEmail("maria.lopez@tuarriendo.cl");
            e3.setCargo("Administrativo");
            e3.setAniosExperiencia(2);
            e3.setSalario(700000.0);
            e3.setActivo(false);
            e3.setFechaContratacion(LocalDate.of(2022, 1, 10));

            empleadoRepository.save(e1);
            empleadoRepository.save(e2);
            empleadoRepository.save(e3);

            log.info("Se han cargado los empleados exitosamente");
        }
    }



}
