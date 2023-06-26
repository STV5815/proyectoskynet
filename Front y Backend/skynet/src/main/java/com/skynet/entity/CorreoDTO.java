package com.skynet.entity;

public class CorreoDTO {
	  private String estado;
	  private Boolean enviado;
	  private String correoUsuario;
	  private String fechaHoraInicio;
	  private String fechaHoraFin;
	  private String coordenadasVisita;
	  private String reporte;
	  
	public CorreoDTO() {
		super();
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Boolean getEnviado() {
		return enviado;
	}
	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}
	public String getCorreoUsuario() {
		return correoUsuario;
	}
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}
	public String getFechaHoraInicio() {
		return fechaHoraInicio;
	}
	public void setFechaHoraInicio(String fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}
	public String getFechaHoraFin() {
		return fechaHoraFin;
	}
	public void setFechaHoraFin(String fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}
	public String getCoordenadasVisita() {
		return coordenadasVisita;
	}
	public void setCoordenadasVisita(String coordenadasVisita) {
		this.coordenadasVisita = coordenadasVisita;
	}
	public String getReporte() {
		return reporte;
	}
	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
	  
	  

}
