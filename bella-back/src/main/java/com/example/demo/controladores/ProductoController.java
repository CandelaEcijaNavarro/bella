package com.example.demo.controladores;

import com.example.demo.entidades.Producto;
import com.example.demo.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAll() {
        return productoService.findAll();
    }

    @GetMapping("/activos")
    public List<Producto> getActive() {
        return productoService.findActive();
    }

    @GetMapping("/{id}")
    public Producto getById(@PathVariable int id) {
        return productoService.findById(id).orElse(null);
    }

    @GetMapping("/nuevos")
    public List<Producto> getNuevos() {
        List<Producto> nuevos = productoService.findRecienLlegados();
        return nuevos;
    }

    @PostMapping
    public Producto save(@RequestBody Producto producto) {
        return productoService.save(producto);
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable int id, @RequestBody Producto producto) {
        producto.setIdProducto(id);
        return productoService.save(producto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productoService.deleteById(id);
    }
}
