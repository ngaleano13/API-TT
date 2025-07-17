package com.ng.talentotech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ng.talentotech.Models.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
}
