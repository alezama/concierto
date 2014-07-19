package com.escom.spring.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the banda database table.
 * 
 */
@Entity
@NamedQuery(name="Banda.findAll", query="SELECT b FROM Banda b")
public class Banda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_banda")
	private int idBanda;

	private String nombre;

	private int ranking;

	//bi-directional many-to-one association to Genero
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_genero")
	private Genero genero;

	//bi-directional many-to-one association to Concierto
	@OneToMany(mappedBy="banda", cascade=CascadeType.ALL)
	private List<Concierto> conciertos;

	public Banda() {
	}

	public int getIdBanda() {
		return this.idBanda;
	}

	public void setIdBanda(int idBanda) {
		this.idBanda = idBanda;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getRanking() {
		return this.ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<Concierto> getConciertos() {
		return this.conciertos;
	}

	public void setConciertos(List<Concierto> conciertos) {
		this.conciertos = conciertos;
	}

	public Concierto addConcierto(Concierto concierto) {
		getConciertos().add(concierto);
		concierto.setBanda(this);

		return concierto;
	}

	public Concierto removeConcierto(Concierto concierto) {
		getConciertos().remove(concierto);
		concierto.setBanda(null);

		return concierto;
	}

}