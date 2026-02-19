package com.example.demo.servicios;

import com.example.demo.entidades.Pedido;
import com.example.demo.entidades.EstadoPedido;
import com.example.demo.repositorios.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    /**
     * Simulation of order delivery process.
     * Transitions statuses based on time elapsed since order creation.
     */
    @Scheduled(fixedRate = 60000) // Check every minute
    public void autoUpdateOrderStatuses() {
        System.out.println("⏰ [PedidoService] Running auto-status transitions...");
        long now = System.currentTimeMillis();

        // PENDIENTE -> EN_CAMINO (after 2-3 minutes)
        List<Pedido> pendientes = pedidoRepository.findByEstado(EstadoPedido.PENDIENTE);
        for (Pedido p : pendientes) {
            if (p.getFechaPedido() != null && (now - p.getFechaPedido().getTime()) > 150000) { // 2.5 min
                p.setEstado(EstadoPedido.EN_CAMINO);
                pedidoRepository.save(p);
                System.out.println("🚚 Pedido #" + p.getIdPedido() + " cambiado a EN_CAMINO");
            }
        }

        // EN_CAMINO -> ENTREGADO (after another 2-3 minutes, i.e., 5 minutes total)
        List<Pedido> enCamino = pedidoRepository.findByEstado(EstadoPedido.EN_CAMINO);
        for (Pedido p : enCamino) {
            if (p.getFechaPedido() != null && (now - p.getFechaPedido().getTime()) > 300000) { // 5 min
                p.setEstado(EstadoPedido.ENTREGADO);
                pedidoRepository.save(p);
                System.out.println("📦 Pedido #" + p.getIdPedido() + " cambiado a ENTREGADO");
            }
        }
    }
}
