package com.duoc.msvehiculo.runner;

import com.duoc.msvehiculo.model.Categoria;
import com.duoc.msvehiculo.model.Vehiculo;
import com.duoc.msvehiculo.repository.CategoriaRepository;
import com.duoc.msvehiculo.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final VehiculoRepository vehiculoRepository;

    public DataLoader(CategoriaRepository categoriaRepository,
                      VehiculoRepository vehiculoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(categoriaRepository.count() == 0){

            Categoria categoria1 = new Categoria();
            categoria1.setNombre("SUV");
            categoria1.setDescripcion("Vehiculos deportivos");
            categoria1.setActivo(true);
            categoria1.setCantidadVehiculos(10);
            categoria1.setFechaRegistro(LocalDate.now());

            Categoria categoria2 = new Categoria();
            categoria2.setNombre("Sedan");
            categoria2.setDescripcion("Vehiculos familiares");
            categoria2.setActivo(true);
            categoria2.setCantidadVehiculos(8);
            categoria2.setFechaRegistro(LocalDate.now());

            Categoria categoria3 = new Categoria();
            categoria3.setNombre("Pickup");
            categoria3.setDescripcion("Vehiculos de carga");
            categoria3.setActivo(true);
            categoria3.setCantidadVehiculos(5);
            categoria3.setFechaRegistro(LocalDate.now());

            categoriaRepository.save(categoria1);
            categoriaRepository.save(categoria2);
            categoriaRepository.save(categoria3);

            Vehiculo vehiculo1 = new Vehiculo();
            vehiculo1.setMarca("Toyota");
            vehiculo1.setModelo("Rav4");
            vehiculo1.setAnio(2022);
            vehiculo1.setPrecioDiario(45000.0);
            vehiculo1.setDisponible(true);
            vehiculo1.setFechaIngreso(LocalDate.now());
            vehiculo1.setCategoria(categoria1);

            Vehiculo vehiculo2 = new Vehiculo();
            vehiculo2.setMarca("Hyundai");
            vehiculo2.setModelo("Accent");
            vehiculo2.setAnio(2021);
            vehiculo2.setPrecioDiario(30000.0);
            vehiculo2.setDisponible(true);
            vehiculo2.setFechaIngreso(LocalDate.now());
            vehiculo2.setCategoria(categoria2);

            Vehiculo vehiculo3 = new Vehiculo();
            vehiculo3.setMarca("Ford");
            vehiculo3.setModelo("Ranger");
            vehiculo3.setAnio(2023);
            vehiculo3.setPrecioDiario(55000.0);
            vehiculo3.setDisponible(false);
            vehiculo3.setFechaIngreso(LocalDate.now());
            vehiculo3.setCategoria(categoria3);

            vehiculoRepository.save(vehiculo1);
            vehiculoRepository.save(vehiculo2);
            vehiculoRepository.save(vehiculo3);

            System.out.println("Datos cargados correctamente");
        }
    }
}
