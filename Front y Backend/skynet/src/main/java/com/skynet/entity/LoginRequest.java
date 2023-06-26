package com.skynet.entity;

import jakarta.persistence.Column;

public class LoginRequest {
    private String nombre;
    private String contrasena;
    @Column(name = "rol_id")
    private Integer rolId;
	public LoginRequest() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public Integer getRolId() {
		return rolId;
	}
	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

  
   
}
