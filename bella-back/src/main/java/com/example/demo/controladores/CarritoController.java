package com.example.demo.controladores;

import com.example.demo.entidades.Carrito;
import com.example.demo.servicios.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping("/usuario/{idUsuario}")
    public List<Carrito> getByUsuario(@PathVariable int idUsuario) {
        return carritoService.findByUsuario(idUsuario);
    }

    @PostMapping
    public Carrito addToCart(@RequestBody Carrito item) {
        return carritoService.addToCart(item);
    }

    @DeleteMapping("/{id}")
    public void removeFromCart(@PathVariable int id) {
        carritoService.removeFromCart(id);
    }

    @DeleteMapping("/usuario/{idUsuario}")
    public void clearCart(@PathVariable int idUsuario) {
        carritoService.clearCart(idUsuario);
    }
}
