package com.ng.talentotech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ng.talentotech.Models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
}
