package com.duoc.runner;


import com.duoc.model.RegionModel;
import com.duoc.model.SucursalesModel;
import com.duoc.repository.RegionRepository;
import com.duoc.repository.SucursalesRepository;
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
    private RegionRepository regionRepository;

    @Autowired
    private SucursalesRepository sucursalesRepository;

    @Override
    public void run(String... args) throws Exception {
        if (regionRepository.count() == 0) {
            log.info("Cargando datos iniciales de regiones...");

            RegionModel r1 = new RegionModel();
            r1.setNombre("Valparaíso");
            r1.setCodigo("V");
            r1.setDescripcion("Región de Valparaíso");
            r1.setNumeroRegion(5);
            r1.setZonaGeografica("Centro");
            r1.setActivo(true);
            r1.setFechaCreacion(LocalDate.of(2020, 1, 1));

            RegionModel r2 = new RegionModel();
            r2.setNombre("Metropolitana");
            r2.setCodigo("RM");
            r2.setDescripcion("Región Metropolitana");
            r2.setNumeroRegion(13);
            r2.setZonaGeografica("Centro");
            r2.setActivo(true);
            r2.setFechaCreacion(LocalDate.of(2020, 1, 1));

            RegionModel r3 = new RegionModel();
            r3.setNombre("Biobío");
            r3.setCodigo("VIII");
            r3.setDescripcion("Región del Biobío");
            r3.setNumeroRegion(8);
            r3.setZonaGeografica("Sur");
            r3.setActivo(true);
            r3.setFechaCreacion(LocalDate.of(2020, 1, 1));

            regionRepository.save(r1);
            regionRepository.save(r2);
            regionRepository.save(r3);

            log.info("Regiones cargadas exitosamente");
        }

        if (sucursalesRepository.count() == 0) {
            log.info("Cargando datos iniciales de sucursales...");

            RegionModel region = regionRepository.findById(1).orElse(null);

            SucursalesModel s1 = new SucursalesModel();
            s1.setNombre("Sucursal Viña del Mar");
            s1.setDescripcion("Sucursal principal de Viña");
            s1.setNumero("001");
            s1.setCapacidad(50);
            s1.setCostoArriendo(100000.0);
            s1.setOperativa(true);
            s1.setFechaApertura(LocalDate.of(2021, 3, 15));
            s1.setRegion(region);

            SucursalesModel s2 = new SucursalesModel();
            s2.setNombre("Sucursal Santiago Centro");
            s2.setDescripcion("Sucursal en el centro de Santiago");
            s2.setNumero("002");
            s2.setCapacidad(80);
            s2.setCostoArriendo(150000.0);
            s2.setOperativa(true);
            s2.setFechaApertura(LocalDate.of(2021, 6, 1));
            s2.setRegion(region);

            SucursalesModel s3 = new SucursalesModel();
            s3.setNombre("Sucursal Concepción");
            s3.setDescripcion("Sucursal en Concepción");
            s3.setNumero("003");
            s3.setCapacidad(40);
            s3.setCostoArriendo(80000.0);
            s3.setOperativa(false);
            s3.setFechaApertura(LocalDate.of(2022, 1, 10));
            s3.setRegion(region);

            sucursalesRepository.save(s1);
            sucursalesRepository.save(s2);
            sucursalesRepository.save(s3);

            log.info("Sucursales cargadas exitosamente");
        }
    }














}
