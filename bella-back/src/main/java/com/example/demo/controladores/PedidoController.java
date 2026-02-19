package com.example.demo.controladores;

import com.example.demo.entidades.Pedido;
import com.example.demo.servicios.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/usuario/{idUsuario}")
    public List<Pedido> getByUsuario(@PathVariable int idUsuario) {
        return pedidoService.findByUsuario(idUsuario);
    }

    @GetMapping("/check-first/{idUsuario}")
    public boolean isFirstOrder(@PathVariable int idUsuario) {
        System.out.println("Comprobando si es el primer pedido para el usuario ID: " + idUsuario);
        long count = pedidoService.countByUsuario(idUsuario);
        System.out.println("Número de pedidos encontrados: " + count);
        return count == 0;
    }

    @GetMapping("/{id}")
    public Pedido getById(@PathVariable int id) {
        return pedidoService.findById(id).orElse(null);
    }

    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        System.out.println("🚀 [PedidoController] Creando nuevo pedido...");
        if (pedido.getEstado() == null) {
            pedido.setEstado(com.example.demo.entidades.EstadoPedido.PENDIENTE);
            System.out.println("   - Estado auto-establecido: PENDIENTE");
        }
        // Date is now handled by @CreationTimestamp in the entity
        Pedido saved = pedidoService.save(pedido);
        System.out.println("✅ Pedido guardado con ID: " + saved.getIdPedido() + " y fecha: " + saved.getFechaPedido());
        return saved;
    }
}
