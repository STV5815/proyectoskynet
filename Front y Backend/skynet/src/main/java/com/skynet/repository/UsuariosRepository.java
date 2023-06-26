package com.skynet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skynet.entity.Usuarios;

public interface UsuariosRepository  extends JpaRepository<Usuarios, Integer>{

	Usuarios findByNombre(String nombre);

	boolean findByNombreAndContrasenaAndRolId(String nombre, String contrasena, Integer rolId);

	boolean existsByNombre(String nombre);
	
	
	@Query("SELECT u FROM Usuarios u INNER JOIN u.rol r")
	List<Usuarios> findAllUsuariosConRol();

	@Query("SELECT u FROM Usuarios u wHERE u.rolId = 3")
	List<Usuarios> findAllWhererolId3();
}
