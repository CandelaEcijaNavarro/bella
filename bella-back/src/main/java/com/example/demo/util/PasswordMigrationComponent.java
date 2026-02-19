package com.example.demo.util;

import com.example.demo.entidades.Usuario;
import com.example.demo.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PasswordMigrationComponent implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>> INICIANDO MIGRACIÓN DE CONTRASEÑAS <<<");
        List<Usuario> usuarios = usuarioService.findAll();
        int count = 0;

        for (Usuario u : usuarios) {
            String pass = u.getPassword();
            // $2a$ is the prefix for BCrypt. If it doesn't have it, it's likely plain text.
            if (pass != null && !pass.startsWith("$2a$")) {
                System.out.println("Encriptando contraseña para usuario: " + u.getEmail());
                u.setPassword(passwordEncoder.encode(pass));
                usuarioService.save(u);
                count++;
            }
        }

        if (count > 0) {
            System.out.println(">>> MIGRACIÓN COMPLETADA: " + count + " usuarios actualizados <<<");
        } else {
            System.out.println(">>> NO SE REQUIERE MIGRACIÓN: Todas las contraseñas ya están encriptadas <<<");
        }
    }
}
