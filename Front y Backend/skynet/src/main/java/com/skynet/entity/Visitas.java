package com.skynet.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Visitas {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	
	@OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Clientes cliente;
	
	@OneToOne
    @JoinColumn(name = "tecnico_id", referencedColumnName = "id")
	private Usuarios usuarios;
	
	private LocalDateTime fecha_hora_inicio;
	
	private LocalDateTime fecha_hora_fin;
	
	private String coordenadas_visita;
	
	private String reporte;
	
	private Boolean enviado;
	
	@OneToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
	private EstadosVisitas estado;

	public Visitas() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	

	public LocalDateTime getFecha_hora_inicio() {
		return fecha_hora_inicio;
	}

	public void setFecha_hora_inicio(LocalDateTime fecha_hora_inicio) {
		this.fecha_hora_inicio = fecha_hora_inicio;
	}

	public LocalDateTime getFecha_hora_fin() {
		return fecha_hora_fin;
	}

	public void setFecha_hora_fin(LocalDateTime fecha_hora_fin) {
		this.fecha_hora_fin = fecha_hora_fin;
	}

	public String getCoordenadas_visita() {
		return coordenadas_visita;
	}

	public void setCoordenadas_visita(String coordenadas_visita) {
		this.coordenadas_visita = coordenadas_visita;
	}

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}

	public Boolean getEnviado() {
		return enviado;
	}

	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}

	public EstadosVisitas getEstado() {
		return estado;
	}

	public void setEstado(EstadosVisitas estado) {
		this.estado = estado;
	}

	
	
}
