package com.escom.spring.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


/**
 * The persistent class for the genero database table.
 * 
 */
@Entity
@NamedQuery(name="Genero.findAll", query="SELECT g FROM Genero g")
public class Genero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_genero")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idGenero;

	private String descripcion;

	//bi-directional many-to-one association to Banda
	@OneToMany(mappedBy="genero", cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private List<Banda> bandas;

	public Genero() {
	}

	public int getIdGenero() {
		return this.idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Banda> getBandas() {
		return this.bandas;
	}

	public void setBandas(List<Banda> bandas) {
		this.bandas = bandas;
	}

	public Banda addBanda(Banda banda) {
		getBandas().add(banda);	
		banda.setGenero(this);

		return banda;
	}

	public Banda removeBanda(Banda banda) {
		getBandas().remove(banda);
		banda.setGenero(null);

		return banda;
	}

}