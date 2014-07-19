package com.escom.spring.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.List;


/**
 * The persistent class for the lugar database table.
 * 
 */
@Entity
@NamedQuery(name="Lugar.findAll", query="SELECT l FROM Lugar l")
public class Lugar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_lugar")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLugar;

	private int capacidad;

	private String direccion;

	private String nombre;

	@Column(name="restriccion_edad", columnDefinition = "BOOLEAN")
	@Type(type = "org.hibernate.type.BooleanType")
	private Boolean restriccionEdad;

	//bi-directional many-to-one association to Concierto
	@OneToMany(mappedBy="lugar")
	private List<Concierto> conciertos;

	public Lugar() {
	}

	public int getIdLugar() {
		return this.idLugar;
	}

	public void setIdLugar(int idLugar) {
		this.idLugar = idLugar;
	}

	public int getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getRestriccionEdad() {
		return this.restriccionEdad;
	}

	public void setRestriccionEdad(Boolean restriccionEdad) {
		this.restriccionEdad = restriccionEdad;
	}

	public List<Concierto> getConciertos() {
		return this.conciertos;
	}

	public void setConciertos(List<Concierto> conciertos) {
		this.conciertos = conciertos;
	}

	public Concierto addConcierto(Concierto concierto) {
		getConciertos().add(concierto);
		concierto.setLugar(this);

		return concierto;
	}

	public Concierto removeConcierto(Concierto concierto) {
		getConciertos().remove(concierto);
		concierto.setLugar(null);

		return concierto;
	}

}