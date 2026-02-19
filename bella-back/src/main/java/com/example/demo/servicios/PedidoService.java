package com.example.demo.servicios;

import com.example.demo.entidades.Pedido;
import com.example.demo.repositorios.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findByUsuario(int idUsuario) {
        return pedidoRepository.findByIdUsuario(idUsuario);
    }

    public long countByUsuario(int idUsuario) {
        return pedidoRepository.findByIdUsuario(idUsuario).size();
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> findById(int id) {
        return pedidoRepository.findById(id);
    }
}
