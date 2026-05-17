package com.duoc.mspagos.repository;
import com.duoc.mspagos.model.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PagosRepository extends JpaRepository<Pagos, Long>{
    @Query("SELECT p FROM Pagos p WHERE p.monto BETWEEN :min AND :max ORDER BY p.fechaPago DESC")
    List<Pagos> buscarPagosPorMonto(@Param("min") Double min,
                                    @Param("max") Double max);
}

