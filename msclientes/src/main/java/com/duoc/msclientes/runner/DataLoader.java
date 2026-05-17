package com.duoc.msclientes.runner;

import com.duoc.msclientes.model.Cliente;
import com.duoc.msclientes.model.Direccion;
import com.duoc.msclientes.repository.ClienteRepository;
import com.duoc.msclientes.repository.DireccionRepository;
import com.duoc.msclientes.service.ClienteService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.logging.Logger;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Override
    public void run(String... args) throws Exception {

        if(clienteRepository.count() == 0){

            Cliente cliente1 = new Cliente();
            cliente1.setNombre("Juan");
            cliente1.setApellido("Tapia");
            cliente1.setRut("11111111-1");
            cliente1.setCorreo("juan@gmail.com");
            cliente1.setTelefono("123456789");
            cliente1.setPuntos(123);
            cliente1.setActivo(true);
            cliente1.setFechaRegistro(LocalDate.now());

            Cliente cliente2 = new Cliente();
            cliente2.setNombre("Maria");
            cliente2.setApellido("Garcia");
            cliente2.setRut("22222222-2");
            cliente2.setCorreo("maria@gmail.com");
            cliente2.setTelefono("987654321");
            cliente2.setPuntos(345);
            cliente2.setActivo(true);
            cliente2.setFechaRegistro(LocalDate.now());

            Cliente cliente3 = new Cliente();
            cliente3.setNombre("Pedro");
            cliente3.setApellido("perez");
            cliente3.setRut("33333333-3");
            cliente3.setCorreo("pedro@gmail.com");
            cliente3.setTelefono("456789123");
            cliente3.setPuntos(678);
            cliente3.setActivo(true);
            cliente3.setFechaRegistro(LocalDate.now());

            clienteRepository.save(cliente1);
            clienteRepository.save(cliente2);
            clienteRepository.save(cliente3);

            Direccion direccion1 = new Direccion();
            direccion1.setCalle("Av lo errazuriz");
            direccion1.setNumero("123");
            direccion1.setComuna("Santiago");
            direccion1.setRegion("Metropolitana");
            direccion1.setCodigoPostal(88383483);
            direccion1.setActiva(true);
            direccion1.setFechaRegistro(LocalDate.now());
            direccion1.setCliente(cliente1);

            Direccion direccion2 = new Direccion();
            direccion2.setCalle("Los Carrera 456");
            direccion2.setNumero("256");
            direccion2.setComuna("Valparaiso");
            direccion2.setRegion("Valparaiso");
            direccion2.setCodigoPostal(8297382);
            direccion2.setActiva(true);
            direccion2.setFechaRegistro(LocalDate.now());
            direccion2.setCliente(cliente2);

            Direccion direccion3 = new Direccion();
            direccion3.setCalle("puangue");
            direccion3.setNumero("789");
            direccion3.setComuna("Santiago");
            direccion3.setRegion("Metropolitana");
            direccion3.setCodigoPostal(982382636);
            direccion3.setActiva(true);
            direccion3.setFechaRegistro(LocalDate.now());
            direccion3.setCliente(cliente3);

            direccionRepository.save(direccion1);
            direccionRepository.save(direccion2);
            direccionRepository.save(direccion3);

            System.out.println("Datos cargados correctamente");
        }
    }
}
