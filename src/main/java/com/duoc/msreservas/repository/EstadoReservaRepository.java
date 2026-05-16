package com.duoc.msreservas.repository;
import com.duoc.msreservas.model.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoReservaRepository extends JpaRepository <EstadoReserva, Long> {

    List<EstadoReserva> findByActivoTrue();
}
