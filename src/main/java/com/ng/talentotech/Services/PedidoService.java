package com.ng.talentotech.Services;

import java.util.List;

import com.ng.talentotech.Models.Pedido;

public interface PedidoService {
    
    List<Pedido> obtenerPedidos();
    Pedido obtenerPorId(Long id);
    Pedido crearPedido(Pedido pedido);
    void eliminarPedido(Long id);

}
