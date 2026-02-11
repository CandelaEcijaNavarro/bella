package com.example.demo.servicios;

import com.example.demo.entidades.Carrito;
import com.example.demo.repositorios.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> findByUsuario(int idUsuario) {
        return carritoRepository.findByIdUsuario(idUsuario);
    }

    public Carrito addToCart(Carrito item) {
        // Logic to increment quantity if already exists could be added here
        return carritoRepository.save(item);
    }

    public void removeFromCart(int idCarrito) {
        carritoRepository.deleteById(idCarrito);
    }

    @Transactional
    public void clearCart(int idUsuario) {
        carritoRepository.deleteByIdUsuario(idUsuario);
    }
}
