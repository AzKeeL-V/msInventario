package com.Inventario.msInventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "productos")
@Data // Genera Getters, Setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "nom_producto", nullable = false, length = 100)
    private String nomProducto;

    @Column(name = "cat_produc", length = 50)
    private String catProduc; // Categoría del producto

    @Column(name = "cant_producto", nullable = false)
    private Integer cantProducto; // Cantidad en stock
}