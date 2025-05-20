// src/main/java/com/Inventario/msInventario/service/ProductoService.java
package com.Inventario.msInventario.service;

import com.Inventario.msInventario.model.Producto;
import com.Inventario.msInventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Integer id, Producto productoDetalles) {
        // Usamos findByIdProducto del repositorio para consistencia si lo definiste
        Optional<Producto> productoExistente = productoRepository.findByIdProducto(id);
        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setNomProducto(productoDetalles.getNomProducto());
            producto.setCatProduc(productoDetalles.getCatProduc());
            producto.setCantProducto(productoDetalles.getCantProducto());
            return productoRepository.save(producto);
        } else {
            return null;
        }
    }

    public boolean borrarProducto(Integer id) {
        if (productoRepository.existsById(id)) { // existsById también funciona con el ID
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Producto> listarTodosLosProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> listarProductoPorId(Integer id) {
        // Usamos findByIdProducto para consistencia si lo definiste
        return productoRepository.findByIdProducto(id);
    }

    public List<Producto> buscarProductosPorNombre(String nombre) {
        return productoRepository.findByNomProductoContainingIgnoreCase(nombre);
    }
}