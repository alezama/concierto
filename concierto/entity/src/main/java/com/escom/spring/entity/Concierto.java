package com.escom.spring.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the concierto database table.
 * 
 */
@Entity
@NamedQuery(name="Concierto.findAll", query="SELECT c FROM Concierto c")
public class Concierto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Concierto de la banda= " + banda.getNombre() +" que se presentará el día " + fecha
				+ " en " + lugar.getNombre();
	}

	@Id
	@Column(name="id_concierto")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idConcierto;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fecha;

	//bi-directional many-to-many association to Cliente
	@ManyToMany
	@Fetch(FetchMode.JOIN)
	@JoinTable(
		name="compra"
		, joinColumns={
			@JoinColumn(name="id_concierto")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_cliente")
			}
		)
	private List<Cliente> clientes;

	//bi-directional many-to-one association to Banda
	@ManyToOne
	@JoinColumn(name="id_banda")
	private Banda banda;

	//bi-directional many-to-one association to Lugar
	@ManyToOne
	@JoinColumn(name="id_lugar")
	private Lugar lugar;

	public Concierto() {
	}

	public int getIdConcierto() {
		return this.idConcierto;
	}

	public void setIdConcierto(int idConcierto) {
		this.idConcierto = idConcierto;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Banda getBanda() {
		return this.banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	public Lugar getLugar() {
		return this.lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public void addCliente (Cliente cliente){
		List<Cliente> listaClientes = this.getClientes();
		listaClientes.add(cliente);
		this.setClientes(listaClientes);
	}
}