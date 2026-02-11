package com.example.demo.entidades;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the categorias database table.
 * 
 */
@Entity
@Table(name = "categorias")
@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	private String descripcion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private int idCategoria;

	private String nombre;

	public Categoria() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}