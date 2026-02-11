package com.example.demo.entidades;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

/**
 * The persistent class for the marcas database table.
 * 
 */
@Entity
@Table(name = "marcas")
@NamedQuery(name = "Marca.findAll", query = "SELECT m FROM Marca m")
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_marca")
	private int idMarca;

	private String nombre;

	// bi-directional many-to-many association to Producto
	@ManyToMany
	@JoinTable(name = "producto_marca", joinColumns = {
			@JoinColumn(name = "id_marca")
	}, inverseJoinColumns = {
			@JoinColumn(name = "id_producto")
	})
	private List<Producto> productos;

	public Marca() {
	}

	public int getIdMarca() {
		return this.idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}