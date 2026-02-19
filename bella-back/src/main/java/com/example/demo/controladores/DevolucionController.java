package com.example.demo.controladores;

import com.example.demo.entidades.Devolucion;
import com.example.demo.entidades.EstadoDevolucion;
import com.example.demo.entidades.EstadoPedido;
import com.example.demo.entidades.Usuario;
import com.example.demo.servicios.DevolucionService;
import com.example.demo.servicios.EmailService;
import com.example.demo.servicios.PedidoService;
import com.example.demo.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/devoluciones")
@CrossOrigin(origins = "*")
public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/usuario/{idUsuario}")
    public List<Devolucion> getByUsuario(@PathVariable int idUsuario) {
        return devolucionService.findByUsuario(idUsuario);
    }

    @GetMapping("/pedido/{idPedido}")
    public List<Devolucion> getByPedido(@PathVariable int idPedido) {
        return devolucionService.findByPedido(idPedido);
    }

    @GetMapping("/{id}")
    public Devolucion getById(@PathVariable int id) {
        return devolucionService.findById(id).orElse(null);
    }

    @PostMapping
    public Devolucion create(@RequestBody Devolucion devolucion) {
        devolucion.setEstado(EstadoDevolucion.PENDIENTE);
        devolucion.setFechaSolicitud(new Timestamp(System.currentTimeMillis()));
        Devolucion saved = devolucionService.save(devolucion);

        // Update Pedido status to CANCELADO
        pedidoService.findById(devolucion.getIdPedido()).ifPresent(p -> {
            p.setEstado(EstadoPedido.CANCELADO);
            pedidoService.save(p);
        });

        // Send confirmation email
        sendReturnConfirmationEmail(saved);

        return saved;
    }

    @PostMapping("/batch")
    public List<Devolucion> createBatch(@RequestBody List<Devolucion> devoluciones) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        for (Devolucion d : devoluciones) {
            d.setEstado(EstadoDevolucion.PENDIENTE);
            d.setFechaSolicitud(now);
        }
        List<Devolucion> saved = devolucionService.saveAll(devoluciones);

        // Update Pedido status to CANCELADO
        if (!devoluciones.isEmpty()) {
            pedidoService.findById(devoluciones.get(0).getIdPedido()).ifPresent(p -> {
                p.setEstado(EstadoPedido.CANCELADO);
                pedidoService.save(p);
            });
        }

        // Send confirmation email for the batch
        if (!saved.isEmpty()) {
            sendBatchReturnConfirmationEmail(saved);
        }

        return saved;
    }

    /**
     * Send confirmation email for a single return
     */
    private void sendReturnConfirmationEmail(Devolucion devolucion) {
        usuarioService.findById(devolucion.getIdUsuario()).ifPresent(usuario -> {
            String subject = "Bella - Confirmación de solicitud de devolución #" + devolucion.getIdDevolucion();
            String body = buildReturnEmailBody(usuario, List.of(devolucion));
            emailService.sendSimpleMessage(usuario.getEmail(), subject, body);
        });
    }

    /**
     * Send confirmation email for a batch of returns (all from same order)
     */
    private void sendBatchReturnConfirmationEmail(List<Devolucion> devoluciones) {
        int idUsuario = devoluciones.get(0).getIdUsuario();
        usuarioService.findById(idUsuario).ifPresent(usuario -> {
            String subject = "Bella - Confirmación de solicitud de devolución (Pedido #"
                    + devoluciones.get(0).getIdPedido() + ")";
            String body = buildReturnEmailBody(usuario, devoluciones);
            emailService.sendSimpleMessage(usuario.getEmail(), subject, body);
        });
    }

    /**
     * Build the email body with return instructions
     */
    private String buildReturnEmailBody(Usuario usuario, List<Devolucion> devoluciones) {
        BigDecimal totalImporte = devoluciones.stream()
                .map(Devolucion::getImporte)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        StringBuilder sb = new StringBuilder();
        sb.append("Hola ").append(usuario.getNombre()).append(",\n\n");
        sb.append("Hemos recibido tu solicitud de devolución correctamente.\n\n");

        sb.append("═══════════════════════════════════════\n");
        sb.append("  DETALLES DE LA DEVOLUCIÓN\n");
        sb.append("═══════════════════════════════════════\n\n");

        sb.append("Pedido: #").append(devoluciones.get(0).getIdPedido()).append("\n");
        sb.append("Productos a devolver: ").append(devoluciones.size()).append("\n");
        sb.append("Importe total del reembolso: ").append(totalImporte).append(" €\n");
        sb.append("Motivo: ").append(devoluciones.get(0).getMotivo()).append("\n\n");

        sb.append("═══════════════════════════════════════\n");
        sb.append("  PRÓXIMOS PASOS\n");
        sb.append("═══════════════════════════════════════\n\n");

        sb.append("1️⃣ INSTRUCCIONES DE ENVÍO\n");
        sb.append("   Recibirás un correo con la etiqueta de envío\n");
        sb.append("   y las instrucciones en las próximas 24 horas.\n\n");

        sb.append("2️⃣ ENVÍO DEL PAQUETE\n");
        sb.append("   Empaqueta los productos en su embalaje original\n");
        sb.append("   y deposítalo en el punto de recogida indicado.\n\n");

        sb.append("3️⃣ REEMBOLSO\n");
        sb.append("   Una vez recibamos tu paquete, procesaremos\n");
        sb.append("   el reembolso en 5-7 días hábiles.\n\n");

        sb.append("═══════════════════════════════════════\n\n");

        sb.append("Si tienes alguna pregunta, no dudes en contactarnos\n");
        sb.append("respondiendo a este correo.\n\n");
        sb.append("Gracias por confiar en Bella. 💄\n\n");
        sb.append("— El equipo de Bella");

        return sb.toString();
    }
}
