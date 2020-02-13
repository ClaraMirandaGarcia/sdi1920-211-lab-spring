package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class Teacher {
	@Id
	@GeneratedValue
	private Long id;

	private String dni;
	private String nombre;
	private String apellidos;
	private String categoría;

	public Teacher() {
	}

	public Teacher(Long id, String dni, String nombre, String apellidos, String categoría) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.categoría = categoría;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCategoría() {
		return categoría;
	}

	public void setCategoría(String categoría) {
		this.categoría = categoría;
	}

	@Override
	public String toString() {
		return "Teacher: id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", categoría="
				+ categoría;
	}

}
