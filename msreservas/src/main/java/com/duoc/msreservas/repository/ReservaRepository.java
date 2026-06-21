package com.duoc.msreservas.repository;
import com.duoc.msreservas.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservaRepository extends JpaRepository <Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.sucursalId = :sucursalId")
    List<Reserva> findBySucursalId(@Param("sucursalId") Integer sucursalId);
}
