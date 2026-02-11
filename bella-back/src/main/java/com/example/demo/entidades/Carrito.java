package com.example.demo.entidades;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the carrito database table.
 * 
 */
@Entity
@Table(name = "carrito")
@NamedQuery(name = "Carrito.findAll", query = "SELECT c FROM Carrito c")
public class Carrito implements Serializable {
	private static final long serialVersionUID = 1L;

	private int cantidad;

	@Column(name = "fecha_creacion")
	private Timestamp fechaCreacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_carrito")
	private int idCarrito;

	@Column(name = "id_producto")
	private int idProducto;

	@Column(name = "id_usuario")
	private int idUsuario;

	public Carrito() {
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getIdCarrito() {
		return this.idCarrito;
	}

	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}

	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}