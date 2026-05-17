package com.duoc.msclientes.repository;
import com.duoc.msclientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {

    Cliente findByRut(String rut);
    List<Cliente> findByCorreoContainingIgnoreCase(String correo);
}
