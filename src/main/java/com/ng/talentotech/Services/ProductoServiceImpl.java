package com.ng.talentotech.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ng.talentotech.Exceptions.ProductoException;
import com.ng.talentotech.Models.Producto;
import com.ng.talentotech.Repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoException("Producto con ID " + id + " no encontrado"));
    }

    @Override
    public Producto creaProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new ProductoException("El nombre del producto no puede estar vacio");
        }
        if (producto.getPrecio() <= 0 || producto.getPrecio() == null) {
            throw new ProductoException("El precio del producto debe ser mayor a 0");
        }
        if (producto.getCantidad() <= 0 || producto.getCantidad() == null) {
            throw new ProductoException("La cantidad tiene que ser mayor a 0");
        }

        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoException("Producto con ID " + id + " no encontrado"));

        if (productoActualizado.getNombre() == null || productoActualizado.getNombre().isBlank()) {
            throw new ProductoException("El nombre del producto no puede estar vacio");
        }
        if (productoActualizado.getPrecio() <= 0) {
            throw new ProductoException("El precio del producto debe ser mayor a 0");
        }
        if (productoActualizado.getCantidad() <= 0) {
            throw new ProductoException("La cantidad no puede ser negativa");
        }

        existente.setNombre(productoActualizado.getNombre());
        existente.setPrecio(productoActualizado.getPrecio());
        existente.setCantidad(productoActualizado.getCantidad());

        return productoRepository.save(existente);
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ProductoException("No se puede eliminar: Producto con ID: " + id + " no encontrado");
        }
        productoRepository.deleteById(id);
    }

}
