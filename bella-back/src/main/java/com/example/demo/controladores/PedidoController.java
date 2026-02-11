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

    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoService.save(pedido);
    }
}
