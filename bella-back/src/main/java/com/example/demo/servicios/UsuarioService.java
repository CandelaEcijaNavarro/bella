package com.example.demo.servicios;

import com.example.demo.entidades.Usuario;
import com.example.demo.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(int id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getFechaRegistro() == null) {
            usuario.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
        }
        if (usuario.getRol() == null) {
            usuario.setRol(com.example.demo.entidades.Rol.USUARIO);
        }

        // Encrypt password if it's plain text (not already starting with $2a$ which is
        // BCrypt prefix)
        String pass = usuario.getPassword();
        if (pass != null && !pass.startsWith("$2a$")) {
            usuario.setPassword(passwordEncoder.encode(pass));
        }

        return usuarioRepository.save(usuario);
    }

    public void deleteById(int id) {
        usuarioRepository.deleteById(id);
    }
}
