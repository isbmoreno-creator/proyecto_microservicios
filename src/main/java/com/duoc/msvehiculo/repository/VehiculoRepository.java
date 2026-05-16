package com.duoc.msvehiculo.repository;
import com.duoc.msvehiculo.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculoRepository extends JpaRepository <com.duoc.msvehiculo.model.Vehiculo, Long> {

    List<Vehiculo> findByDisponibleTrueAndPrecioDiarioLessThan(Double precio);
}
