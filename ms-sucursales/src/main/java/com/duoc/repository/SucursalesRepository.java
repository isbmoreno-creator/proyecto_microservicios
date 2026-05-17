package com.duoc.repository;

import com.duoc.model.SucursalesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalesRepository extends JpaRepository<SucursalesModel, Integer> {
    @Query(value =  "SELECT * FROM sucursal WHERE operativa = true ORDER BY nombre ASC", nativeQuery = true)
    List<SucursalesModel> findSucursalesOperativasOrdenadas();
}
