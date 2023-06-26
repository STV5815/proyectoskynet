package com.skynet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skynet.entity.Clientes;
import com.skynet.entity.EstadosVisitas;
import com.skynet.entity.Roles;
import com.skynet.entity.Usuarios;
import com.skynet.entity.Visitas;
import com.skynet.repository.ClientesRepository;
import com.skynet.repository.EstadosRepository;
import com.skynet.repository.RolesRepository;
import com.skynet.repository.UsuariosRepository;
import com.skynet.repository.VisitasRepository;

@Controller
@RequestMapping("api/")
public class APIController {

	@Autowired
	ClientesRepository clienteRepository;
	@Autowired
	UsuariosRepository usuariosRepository;
	@Autowired
	RolesRepository rolesRepository;
	@Autowired
	EstadosRepository estadosRepository;
	
	@Autowired
	VisitasRepository visitasRepository;
	
	//CLIENTES
	@GetMapping("clientes")
    @ResponseBody
    public List<Clientes> getAllClientes() {
        return clienteRepository.findAll();
    }
	
	@GetMapping("clientes/{id}")
    public ResponseEntity<Clientes> getClientesById(@PathVariable("id") int id) {
        Optional<Clientes> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@PostMapping("clientes")
    public ResponseEntity<Clientes> crearCliente(@RequestBody Clientes cliente) {
        Clientes nuevoCliente = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }
	
	
	//USUARIOS
	@GetMapping("usuarios")
	@ResponseBody
	public List<Usuarios> getAllUsuarios(){
		return usuariosRepository.findAll();
	}
	
	@GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuarios> getUsuariosByID(@PathVariable("id") int id) {
        Optional<Usuarios> usuario = usuariosRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@PostMapping("/crear-usuario")
    public ResponseEntity<Usuarios> crearUsuario(@RequestBody Usuarios usuario) {
        Usuarios nuevoUsuario = usuariosRepository.save(usuario);
        Integer rolId = usuario.getRolId();
		Roles rol = rolesRepository.findById(rolId).orElse(null);
		usuario.setRol(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
	
	
	
	//ROLES
	@GetMapping("roles")
	@ResponseBody
	public List<Roles> getAllRoles(){
		return rolesRepository.findAll();
	}
	
	@GetMapping("roles/{id}")
	public ResponseEntity<Roles> getRolesById(@PathVariable("id") int id){
		Optional<Roles> rol = rolesRepository.findById(id);
		if (rol.isPresent()) {
			return ResponseEntity.ok(rol.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/crear-rol")
    public ResponseEntity<Roles> crearRol(@RequestBody Roles roles) {
        Roles rol = rolesRepository.save(roles);
        return ResponseEntity.status(HttpStatus.CREATED).body(rol);
    }
	
	
	//ESTADOS VISITAS
	@GetMapping("estados")
	@ResponseBody
	public List<EstadosVisitas> getAllEstados(){
		return estadosRepository.findAll();
	}
	
	@GetMapping("estados/{id}")
    public ResponseEntity<EstadosVisitas> getEstadosById(@PathVariable("id") int id) {
        Optional<EstadosVisitas> estados = estadosRepository.findById(id);
        if (estados.isPresent()) {
            return ResponseEntity.ok(estados.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@PostMapping("/crear-estado")
    public ResponseEntity<EstadosVisitas> crearEstado(@RequestBody EstadosVisitas estadosVisitas) {
        EstadosVisitas estados = estadosRepository.save(estadosVisitas);
        return ResponseEntity.status(HttpStatus.CREATED).body(estados);
    }
	
	//VISITAS
	@GetMapping("visitas")
    @ResponseBody
    public List<Visitas> getAllVisitas() {
        return visitasRepository.findAll();
    }
	
}
