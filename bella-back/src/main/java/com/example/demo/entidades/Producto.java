package com.example.demo.entidades;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name = "productos")
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private int idProducto;

	@Column(name = "activo")
	private boolean activo = true;

	@Lob
	private String descripcion;

	@Column(name = "id_categoria")
	private int idCategoria;

	private String imagen;

	private String nombre;

	@Column(name = "numero_resenas")
	private Integer numeroResenas;

	private BigDecimal precio;

	@Column(name = "puntuacion_media")
	private BigDecimal puntuacionMedia;

	private int stock;

	@Column(name = "precio_original")
	private BigDecimal precioOriginal;

	@Column(name = "en_oferta")
	private boolean enOferta = false;

	@Column(name = "es_nuevo")
	private boolean esNuevo = true;

	@Column(name = "fecha_creacion")
	private java.time.LocalDateTime fechaCreacion = java.time.LocalDateTime.now();

	public Producto() {
	}

	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroResenas() {
		return this.numeroResenas;
	}

	public void setNumeroResenas(Integer numeroResenas) {
		this.numeroResenas = numeroResenas;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public BigDecimal getPuntuacionMedia() {
		return this.puntuacionMedia;
	}

	public void setPuntuacionMedia(BigDecimal puntuacionMedia) {
		this.puntuacionMedia = puntuacionMedia;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public BigDecimal getPrecioOriginal() {
		return precioOriginal;
	}

	public void setPrecioOriginal(BigDecimal precioOriginal) {
		this.precioOriginal = precioOriginal;
	}

	public boolean isEnOferta() {
		return enOferta;
	}

	public void setEnOferta(boolean enOferta) {
		this.enOferta = enOferta;
	}

	public boolean isEsNuevo() {
		return esNuevo;
	}

	public void setEsNuevo(boolean esNuevo) {
		this.esNuevo = esNuevo;
	}

	public java.time.LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(java.time.LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}