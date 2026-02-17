package com.example.demo.controladores;

import com.example.demo.entidades.Mensaje;
import com.example.demo.repositorios.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular frontend
public class ChatController {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private com.example.demo.servicios.EmailService emailService;

    // In-memory storage for verification codes (Email -> Code)
    // In production, use Redis or Database with expiration
    private java.util.Map<String, String> verificationCodes = new java.util.concurrent.ConcurrentHashMap<>();

    @PostMapping("/enviar")
    public ResponseEntity<?> enviarMensaje(@RequestBody Mensaje mensaje) {
        mensajeRepository.save(mensaje);
        return ResponseEntity.ok().body("{\"status\": \"Mensaje recibido\"}");
    }

    @PostMapping("/send-code")
    public ResponseEntity<?> sendVerificationCode(@RequestBody java.util.Map<String, String> payload) {
        String email = payload.get("email");
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("{\"error\": \"Email is required\"}");
        }

        // Generate 6-digit code
        String code = String.valueOf((int) (Math.random() * 900000) + 100000);
        verificationCodes.put(email, code);

        // Send email
        String subject = "Código de Verificación - B'ELLA";
        String text = "Tu código de verificación es: " + code + "\n\nPor favor, ingrésalo en el chat para continuar.";
        emailService.sendSimpleMessage(email, subject, text);

        return ResponseEntity.ok().body("{\"status\": \"Code sent\"}");
    }

    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestBody java.util.Map<String, String> payload) {
        String email = payload.get("email");
        String code = payload.get("code");

        if (email == null || code == null) {
            return ResponseEntity.badRequest().body("{\"error\": \"Email and code are required\"}");
        }

        String storedCode = verificationCodes.get(email);
        if (storedCode != null && storedCode.equals(code)) {
            verificationCodes.remove(email); // One-time use
            return ResponseEntity.ok().body("{\"verified\": true}");
        } else {
            return ResponseEntity.status(401).body("{\"verified\": false, \"error\": \"Invalid code\"}");
        }
    }
}
