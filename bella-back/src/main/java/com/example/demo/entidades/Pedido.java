package com.example.demo.entidades;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * The persistent class for the pedidos database table.
 * 
 */
@Entity
@Table(name = "pedidos")
@NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private int idPedido;

	@Enumerated(EnumType.STRING)
	private EstadoPedido estado;

	@CreationTimestamp
	@Column(name = "fecha_pedido", updatable = false)
	private Timestamp fechaPedido;

	@Column(name = "id_usuario")
	private int idUsuario;

	@Lob
	private String productos;

	private BigDecimal total;

	public Pedido() {
	}

	public int getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public EstadoPedido getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public Timestamp getFechaPedido() {
		return this.fechaPedido;
	}

	public void setFechaPedido(Timestamp fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getProductos() {
		return this.productos;
	}

	public void setProductos(String productos) {
		this.productos = productos;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}