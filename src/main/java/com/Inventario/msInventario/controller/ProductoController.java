// src/main/java/com/Inventario/msInventario/controller/ProductoController.java
package com.Inventario.msInventario.controller;

import com.Inventario.msInventario.model.Producto;
import com.Inventario.msInventario.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos") // Prefijo base para todas las rutas de este controlador
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Ruta: POST /api/productos/guardar
    @PostMapping("/guardar")                           //aqui abajo en el request body manda el archivo 
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.agregarProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED); // code 201 Created
    }

    // Ruta: GET /api/productos/listar
    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.listarTodosLosProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK); // code 200 OK
    }

    // Ruta: GET /api/productos/buscarPorId/{id}
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Producto> buscarProductoPorId(@PathVariable Integer id) {
        Optional<Producto> producto = productoService.listarProductoPorId(id);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // code 200 OK
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // code 404 Not Found
    }

    // Ruta: GET /api/productos/buscarPorNombre
    // Requerimiento funcional: buscarProductosPorNombre()
    @GetMapping("/buscarPorNombre")
    public ResponseEntity<List<Producto>> buscarProductosPorNombre(@RequestParam String nombre) {
        List<Producto> productos = productoService.buscarProductosPorNombre(nombre);
        if (!productos.isEmpty()) {
            return new ResponseEntity<>(productos, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // Ruta: PUT /api/productos/actualizar/{id}
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizarProducto(id, producto);
        //se guardo correctamente?
        if (productoActualizado != null) {
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK); // 200 OK
        } else {
            //error
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // Ruta: DELETE /api/productos/eliminar/{id}
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        boolean eliminado = productoService.borrarProducto(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}