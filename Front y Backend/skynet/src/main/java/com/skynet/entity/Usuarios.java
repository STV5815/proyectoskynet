package com.skynet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Usuarios {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    private String nombre;

	    private String email;

	    private String contrasena;

	    @Column(name = "id_rol", insertable = false, updatable = false)
	    private Integer rolId;

	    @OneToOne
	    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
	    private Roles rol;
	    
		public Usuarios() {
			super();
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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

		public Roles getRol() {
			return rol;
		}

		public void setRol(Roles rol) {
			this.rol = rol;
		}

		
		

	    
	
}
