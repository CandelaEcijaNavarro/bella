package com.example.demo.repositorios;

import com.example.demo.entidades.Devolucion;
import com.example.demo.entidades.EstadoDevolucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Integer> {
    List<Devolucion> findByIdUsuario(int idUsuario);

    List<Devolucion> findByIdPedido(int idPedido);

    List<Devolucion> findByEstado(EstadoDevolucion estado);
}
