package com.duoc.msempleados.repository;

import com.duoc.msempleados.model.EmpleadoModel;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface EmpleadoRepository extends JpaRepository<EmpleadoModel, Integer > {
 @Query(value = "SELECT * FROM empleado WHERE activo = true AND YEAR(fecha_contratacion) = :anio", nativeQuery = true)
 List<EmpleadoModel> findEmpleadoActivosPorAnio(@Param("anio") Integer anio);
}
