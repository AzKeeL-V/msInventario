// src/main/java/com/Inventario/msInventario/repository/ProductoRepository.java
package com.Inventario.msInventario.repository;

import com.Inventario.msInventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Método para buscar productos por nombre (ignorando mayúsculas/minúsculas y subcadena)
    List<Producto> findByNomProductoContainingIgnoreCase(String nomProducto);

    // Método explícito para buscar por idProducto (redundante con JpaRepository.findById, pero si lo prefieres)
    Optional<Producto> findByIdProducto(Integer idProducto);
}