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

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @GetMapping
    public java.util.List<Usuario> getAll() {
        return usuarioService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        usuarioService.deleteById(id);
    }

    @PostMapping("/registro")
    public Usuario registro(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario loginReq) {
        return usuarioService.findByEmail(loginReq.getEmail())
                .filter(u -> passwordEncoder.matches(loginReq.getPassword(), u.getPassword()))
                .orElse(null);
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable int id) {
        return usuarioService.findById(id).orElse(null);
    }

    @PutMapping("/update")
    public Usuario update(@RequestBody Usuario usuario, @RequestParam(required = false) String currentPassword) {
        try {
            System.out.println("Actualizando usuario ID: " + usuario.getIdUsuario());
            Usuario existingUser = usuarioService.findById(usuario.getIdUsuario()).orElse(null);
            if (existingUser == null) {
                System.out.println("Usuario no encontrado");
                return null;
            }

            // 1. Password Logic: Only change if password is provided in the request
            if (usuario.getPassword() != null && !usuario.getPassword().trim().isEmpty()) {
                // If changing password, verify the current one
                if (currentPassword == null || !passwordEncoder.matches(currentPassword, existingUser.getPassword())) {
                    throw new RuntimeException("Contraseña actual incorrecta");
                }
                existingUser.setPassword(usuario.getPassword());
            }

            // 2. Partial Update: Copy other non-null fields
            if (usuario.getNombre() != null)
                existingUser.setNombre(usuario.getNombre());
            if (usuario.getCalle() != null)
                existingUser.setCalle(usuario.getCalle());
            if (usuario.getCiudad() != null)
                existingUser.setCiudad(usuario.getCiudad());
            if (usuario.getCodigoPostal() != null)
                existingUser.setCodigoPostal(usuario.getCodigoPostal());
            if (usuario.getProvincia() != null)
                existingUser.setProvincia(usuario.getProvincia());
            if (usuario.getPais() != null)
                existingUser.setPais(usuario.getPais());
            if (usuario.getTelefono() != null)
                existingUser.setTelefono(usuario.getTelefono());
            if (usuario.getImagenPerfil() != null)
                existingUser.setImagenPerfil(usuario.getImagenPerfil());
            if (usuario.getFavoritos() != null)
                existingUser.setFavoritos(usuario.getFavoritos());

            return usuarioService.save(existingUser);
        } catch (Exception e) {
            System.err.println("ERROR EN UPDATE: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
