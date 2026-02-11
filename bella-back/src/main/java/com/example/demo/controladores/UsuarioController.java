package com.example.demo.controladores;

import com.example.demo.entidades.Usuario;
import com.example.demo.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public Usuario registro(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario loginReq) {
        return usuarioService.findByEmail(loginReq.getEmail())
                .filter(u -> u.getPassword().equals(loginReq.getPassword()))
                .orElse(null);
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable int id) {
        return usuarioService.findById(id).orElse(null);
    }
}
