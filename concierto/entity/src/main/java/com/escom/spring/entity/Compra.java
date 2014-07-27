package com.escom.spring.entity;

import java.util.List;

public class Compra {
	
	
	private Integer seleccionCompra;
	private Integer idCliente;
	private Integer numeroBoletos;
	private Integer idLugar;
	private Integer idBanda;
	private Integer idConcierto;
	private List<Concierto> listaConciertos;
	
	
	
	
	public Integer getSeleccionCompra() {
		return seleccionCompra;
	}
	public void setSeleccionCompra(Integer seleccionCompra) {
		this.seleccionCompra = seleccionCompra;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getNumeroBoletos() {
		return numeroBoletos;
	}
	public void setNumeroBoletos(Integer numeroBoletos) {
		this.numeroBoletos = numeroBoletos;
	}
	public Integer getIdLugar() {
		return idLugar;
	}
	public void setIdLugar(Integer idLugar) {
		this.idLugar = idLugar;
	}
	public Integer getIdBanda() {
		return idBanda;
	}
	public void setIdBanda(Integer idBanda) {
		this.idBanda = idBanda;
	}
	public Integer getIdConcierto() {
		return idConcierto;
	}
	public void setIdConcierto(Integer idConcierto) {
		this.idConcierto = idConcierto;
	}
	public List<Concierto> getListaConciertos() {
		return listaConciertos;
	}
	public void setListaConciertos(List<Concierto> listaConciertos) {
		this.listaConciertos = listaConciertos;
	}

	
}
