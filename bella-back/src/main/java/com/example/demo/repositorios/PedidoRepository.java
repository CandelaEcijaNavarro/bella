package com.example.demo.repositorios;

import com.example.demo.entidades.Pedido;
import com.example.demo.entidades.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByIdUsuario(int idUsuario);

    List<Pedido> findByEstado(EstadoPedido estado);
}
