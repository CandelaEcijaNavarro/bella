package com.example.demo.servicios;

import com.example.demo.entidades.Devolucion;
import com.example.demo.entidades.EstadoDevolucion;
import com.example.demo.entidades.Usuario;
import com.example.demo.repositorios.DevolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class DevolucionService {

    @Autowired
    private DevolucionRepository devolucionRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioService usuarioService;

    public List<Devolucion> findByUsuario(int idUsuario) {
        return devolucionRepository.findByIdUsuario(idUsuario);
    }

    public List<Devolucion> findByPedido(int idPedido) {
        return devolucionRepository.findByIdPedido(idPedido);
    }

    public Optional<Devolucion> findById(int id) {
        return devolucionRepository.findById(id);
    }

    public Devolucion save(Devolucion devolucion) {
        return devolucionRepository.save(devolucion);
    }

    public List<Devolucion> saveAll(List<Devolucion> devoluciones) {
        return devolucionRepository.saveAll(devoluciones);
    }

    /**
     * Scheduled task: runs every day at 9:00 AM.
     * Checks for returns with estado=PENDIENTE that are older than 7 business days
     * and automatically changes their status to REEMBOLSADA.
     */
    @Scheduled(cron = "0 0 9 * * *") // Every day at 9:00 AM
    public void autoProcessReturns() {
        System.out.println("⏰ [DevolucionService] Running scheduled auto-process for returns...");

        // Calculate date 7 business days ago (approx 10 calendar days to account for
        // weekends)
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -10); // ~7 business days
        Timestamp cutoffDate = new Timestamp(cal.getTimeInMillis());

        List<Devolucion> pendientes = devolucionRepository.findByEstado(EstadoDevolucion.PENDIENTE);
        int processed = 0;

        for (Devolucion dev : pendientes) {
            if (dev.getFechaSolicitud() != null && dev.getFechaSolicitud().before(cutoffDate)) {
                dev.setEstado(EstadoDevolucion.REEMBOLSADA);
                dev.setFechaResolucion(new Timestamp(System.currentTimeMillis()));
                devolucionRepository.save(dev);
                processed++;

                // Send refund confirmation email
                sendRefundEmail(dev);
            }
        }

        System.out.println("✅ [DevolucionService] Auto-processed " + processed + " returns to REEMBOLSADA.");
    }

    /**
     * Send refund confirmation email to the user
     */
    private void sendRefundEmail(Devolucion devolucion) {
        Optional<Usuario> optUser = usuarioService.findById(devolucion.getIdUsuario());
        if (optUser.isPresent()) {
            Usuario usuario = optUser.get();
            String subject = "Bella - Tu reembolso ha sido procesado ✨";
            String body = "Hola " + usuario.getNombre() + ",\n\n"
                    + "¡Buenas noticias! Tu reembolso ha sido procesado correctamente.\n\n"
                    + "═══════════════════════════════════════\n"
                    + "  DETALLES DEL REEMBOLSO\n"
                    + "═══════════════════════════════════════\n\n"
                    + "Pedido: #" + devolucion.getIdPedido() + "\n"
                    + "Importe reembolsado: " + devolucion.getImporte() + " €\n"
                    + "Fecha de resolución: " + devolucion.getFechaResolucion() + "\n\n"
                    + "El importe se verá reflejado en tu cuenta bancaria\n"
                    + "en un plazo de 2-3 días laborables.\n\n"
                    + "Gracias por tu paciencia y confianza en Bella. 💄\n\n"
                    + "— El equipo de Bella";

            emailService.sendSimpleMessage(usuario.getEmail(), subject, body);
        }
    }
}
