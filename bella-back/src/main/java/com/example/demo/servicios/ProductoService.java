package com.example.demo.servicios;

import com.example.demo.entidades.Producto;
import com.example.demo.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public List<Producto> findActive() {
        return productoRepository.findByActivo(true);
    }

    public Optional<Producto> findById(int id) {
        return productoRepository.findById(id);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteById(int id) {
        productoRepository.deleteById(id);
    }
}
