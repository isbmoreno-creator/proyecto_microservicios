package com.duoc.msclientes.repository;
import com.duoc.msclientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClienteRepository extends JpaRepository <Cliente, Long> {
}
