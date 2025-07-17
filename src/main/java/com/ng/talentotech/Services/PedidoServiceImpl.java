package com.ng.talentotech.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ng.talentotech.Exceptions.PedidoException;
import com.ng.talentotech.Models.Pedido;
import com.ng.talentotech.Models.Producto;
import com.ng.talentotech.Repository.PedidoRepository;
import com.ng.talentotech.Repository.ProductoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido obtenerPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoException("Pedido con ID " + id + " no encontrado"));
    }

    public Pedido crearPedido(Pedido pedido) {
        if (pedido.getProductos() == null || pedido.getProductos().isEmpty()) {
            throw new PedidoException("El pedido debe contener al menos un producto");
        }
        List<Producto> productosBase = pedido.getProductos().stream()
                .<Producto>map(p -> {
                    Long id = p.getId();
                    if (id == null) {
                        throw new PedidoException("Se recibio un producto sin ID");
                    }
                    return productoRepository.findById(id)
                            .orElseThrow(() -> new PedidoException("Producto con ID " + id + " no encontrado"));
                })
                .collect(Collectors.toList());

        double total = productosBase.stream()
                .mapToDouble(p -> p.getPrecio() != null ? p.getPrecio() : 0)
                .sum();

        pedido.setProductos(productosBase);
        pedido.setFecha(LocalDateTime.now());
        pedido.setTotal(total);

        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new PedidoException("No se puede eliminar: Pedido con ID " + id + " no encontrado");
        }
        pedidoRepository.deleteById(id);
    }

}
