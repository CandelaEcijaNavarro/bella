package com.example.demo.repositorios;

import com.example.demo.entidades.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    List<Carrito> findByIdUsuario(int idUsuario);

    void deleteByIdUsuario(int idUsuario);
}
