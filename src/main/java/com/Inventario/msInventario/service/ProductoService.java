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

    public Producto agregarProducto(Producto productoNuevo) {
        // Busca si ya existe un producto con el mismo nombre
        Optional<Producto> productoExistente = productoRepository.findByNomProducto(productoNuevo.getNomProducto());
        // O si usaste: productoRepository.findByNomProductoIgnoreCase(productoNuevo.getNomProducto());


        if (productoExistente.isPresent()) {
            // Si el producto ya existe, actualiza su cantidad
            Producto producto = productoExistente.get();
            producto.setCantProducto(producto.getCantProducto() + productoNuevo.getCantProducto());
            // producto.setCatProduc(productoNuevo.getCatProduc());
            return productoRepository.save(producto); // Guarda el producto actualizado
        } else {
            // Si el producto no existe, guárdalo como uno nuevo
            return productoRepository.save(productoNuevo);
        }
    }

    public Producto actualizarProducto(Integer id, Producto productoDetalles) {
        // Este método está bien para actualizar un producto *específico* por su ID
        Optional<Producto> productoExistente = productoRepository.findByIdProducto(id);
        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setNomProducto(productoDetalles.getNomProducto());
            producto.setCatProduc(productoDetalles.getCatProduc());
            producto.setCantProducto(productoDetalles.getCantProducto());
            return productoRepository.save(producto);
        } else {
            return null; // O lanza una excepción, según tu manejo de errores
        }
    }

    public boolean borrarProducto(Integer id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Producto> listarTodosLosProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> listarProductoPorId(Integer id) {
        return productoRepository.findByIdProducto(id);
    }

    public List<Producto> buscarProductosPorNombre(String nombre) {
        return productoRepository.findByNomProductoContainingIgnoreCase(nombre);
    }
}