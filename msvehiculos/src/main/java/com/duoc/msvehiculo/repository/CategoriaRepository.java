package com.duoc.msvehiculo.repository;
import com.duoc.msvehiculo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository <Categoria, Long> {
}
