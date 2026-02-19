package com.example.demo.entidades;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The persistent class for the devoluciones database table.
 */
@Entity
@Table(name = "devoluciones")
@NamedQuery(name = "Devolucion.findAll", query = "SELECT d FROM Devolucion d")
public class Devolucion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devolucion")
    private int idDevolucion;

    @Column(name = "id_pedido")
    private int idPedido;

    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "id_producto")
    private int idProducto;

    private String motivo;

    @Enumerated(EnumType.STRING)
    private EstadoDevolucion estado;

    @Column(name = "fecha_solicitud")
    private Timestamp fechaSolicitud;

    @Column(name = "fecha_resolucion")
    private Timestamp fechaResolucion;

    private BigDecimal importe;

    public Devolucion() {
    }

    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstadoDevolucion getEstado() {
        return estado;
    }

    public void setEstado(EstadoDevolucion estado) {
        this.estado = estado;
    }

    public Timestamp getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Timestamp fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Timestamp getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Timestamp fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
}
