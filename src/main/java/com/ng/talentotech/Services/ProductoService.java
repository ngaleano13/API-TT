package com.ng.talentotech.Services;

import java.util.List;

import com.ng.talentotech.Models.Producto;

public interface ProductoService {
    
    List<Producto> obtenerProductos();
    Producto obtenerPorId(Long id);
    Producto creaProducto(Producto producto);
    Producto actualizarProducto (Long id, Producto producto);
    void eliminarProducto(Long id);
}
