package com.skynet.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import com.skynet.entity.Clientes;
import com.skynet.entity.Roles;
import com.skynet.entity.Usuarios;
import com.skynet.entity.Visitas;
import com.skynet.repository.ClientesRepository;
import com.skynet.repository.EstadosRepository;
import com.skynet.repository.RolesRepository;
import com.skynet.repository.UsuariosRepository;
import com.skynet.repository.VisitasRepository;

import jakarta.servlet.http.HttpSession;

@Controller

public class ViewController {

	@Autowired
	ClientesRepository clienteRepository;
	@Autowired
	UsuariosRepository usuariosRepository;
	@Autowired
	RolesRepository rolesRepository;
	@Autowired
	VisitasRepository visitasRepository;
	@Autowired
	EstadosRepository estadosRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;

	
	@GetMapping("/")
	public String showLoginForm() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("nombre") String nombre, @RequestParam("contrasena") String contrasena,
			HttpSession session, Model model) {

		Usuarios usuario = usuariosRepository.findByNombre(nombre);

		if (usuario != null && usuario.getContrasena().equals(contrasena)) {
	        session.setAttribute("userId", usuario.getId());


			switch (usuario.getRolId()) {
			case 1:
				return "redirect:/admin";
			case 2:
				return "redirect:/supervisor";
			case 3:
				return "redirect:/tecnico";
			default:

				return "redirect:/errorLogin";
			}
		} else {

			model.addAttribute("error", "Credenciales inválidas");
			return "errorLogin";
		}
	}

	
	@GetMapping("/supervisor")
	public String showSupervisor(HttpSession session) {
		if (session.getAttribute("userId") != null) {
			return "supervisor";
		} else {
			 return "redirect:/";
		}
		
	}

	@GetMapping("/tecnico")
	public String showTecnico(HttpSession session) {
		if (session.getAttribute("userId") != null) {
			return "tecnico";
		} else {
			 return "redirect:/";
		}
		
	}

	@GetMapping("/admin")
	public String showAdmin(HttpSession session) {
		return "admin";
	}

	// USUARIOS
	@GetMapping("/usuarios")
	public String getAllUsuarios(Model model, HttpSession session) {

		model.addAttribute("listaUsuarios", usuariosRepository.findAllUsuariosConRol());
		return "usuarios";
	}

	@GetMapping("/crearUsuario")
	public String crearUsuario(Model model, HttpSession session) {
		Usuarios usuario = new Usuarios();
		model.addAttribute("usuario", usuario);
		model.addAttribute("roles", rolesRepository.findAll());

		return "addUsuario";
	}

	@PostMapping("/guardarUsuario")
	public String guardarUsuario(@ModelAttribute("usuario") Usuarios usuario, Model model, HttpSession session) {
		if (usuariosRepository.existsByNombre(usuario.getNombre())) {
			model.addAttribute("errorMessage", "Error el nombre de usuario que quieres guardar ya existe.");
			model.addAttribute("roles", rolesRepository.findAll());
			return "addUsuario";

		}
		if (usuario.getNombre().isBlank() || usuario.getEmail().isBlank() || usuario.getContrasena().isBlank()) {
			model.addAttribute("errorMessage", "Debes Llenar todos los campos");
			model.addAttribute("roles", rolesRepository.findAll());
			return "addUsuario";

		}
		Integer rolId = usuario.getRolId();
		Roles rol = rolesRepository.findById(rolId).orElse(null);
		usuario.setRol(rol);
		usuariosRepository.save(usuario);
		return "redirect:/usuarios";
	}

	@GetMapping("/actualizarUsuario/{id}")
	public String actualizarUsuarioPagina(@PathVariable("id") int id, Model model, HttpSession session) {
		Optional<Usuarios> temp = usuariosRepository.findById(id);
		Usuarios usuarioA = temp.get();
		model.addAttribute("usuarioA", usuarioA);
		model.addAttribute("roles", rolesRepository.findAll());

		return "actualizarusuario";
	}

	@PostMapping("/actualizaUsuario")
	public String actualizaUsuario(@ModelAttribute("usuario") Usuarios usuario, Model model) {

		if (usuario.getNombre().isBlank() || usuario.getEmail().isBlank() || usuario.getContrasena().isBlank()) {
			model.addAttribute("errorMessage", "Debes Llenar todos los campos");
			model.addAttribute("roles", rolesRepository.findAll());
			return "addUsuario";

		}
		Integer rolId = usuario.getRolId();
		Roles rol = rolesRepository.findById(rolId).orElse(null);
		usuario.setRol(rol);

		usuariosRepository.save(usuario);
		return "redirect:/usuarios";
	}

	@GetMapping("/borrarUsuario/{id}")
	public String borrarUsuario(@PathVariable("id") int id) {
		usuariosRepository.deleteById(id);
		return "redirect:/usuarios";
	}

	// ************************************************************
	// ROLES

	@GetMapping("/roles")
	public String getAllRoles(Model model, HttpSession session) {

		model.addAttribute("listaRoles", rolesRepository.findAll());
		return "roles";
	}

	@GetMapping("/crearRol")
	public String crearRol(Model model, HttpSession session) {
		Roles rol = new Roles();
		model.addAttribute("rol", rol);

		return "addrol";
	}

	@PostMapping("/guardarRol")
	public String guardarRol(@ModelAttribute("rol") Roles rol, HttpSession session) {
		rolesRepository.save(rol);
		return "redirect:/roles";
	}

	@GetMapping("/actualizarRol/{id}")
	public String actualizarRol(@PathVariable("id") int id, Model model, HttpSession session) {
		Optional<Roles> temp = rolesRepository.findById(id);
		Roles rolA = temp.get();
		model.addAttribute("rolA", rolA);
		return "actualizarol";
	}

	@GetMapping("/borrarRol/{id}")
	public String borrarRol(@PathVariable("id") int id, HttpSession session) {
		rolesRepository.deleteById(id);
		return "redirect:/roles";
	}

	// ************************************************************
	// ROLES

	@GetMapping("/clientes")
	public String getAllClientes(Model model, HttpSession session) {
		model.addAttribute("listaClientes", clienteRepository.findAll());
		return "clientes";
	}

	@GetMapping("/crearCliente")
	public String crearCliente(Model model, HttpSession session) {
		Clientes cliente = new Clientes();
		model.addAttribute("cliente", cliente);

		return "addcliente";
	}

	@PostMapping("/guardarCliente")
	public String guardarCliente(@ModelAttribute("cliente") Clientes cliente) {
		clienteRepository.save(cliente);
		return "redirect:/clientes";
	}

	@GetMapping("/actualizarCliente/{id}")
	public String actualizarCliente(@PathVariable("id") int id, Model model) {
		Optional<Clientes> temp = clienteRepository.findById(id);
		Clientes cliente = temp.get();
		model.addAttribute("cliente", cliente);
		return "actualizarcliente";
	}

	@GetMapping("/borrarCliente/{id}")
	public String borrarCliente(@PathVariable("id") int id, Model model) {
		
		clienteRepository.deleteById(id);
		return "redirect:/clientes";
	}

	// VISITAS
	@GetMapping("/visitas")
	public String getAllVisitas(Model model) {

		model.addAttribute("listaVisitas", visitasRepository.findAll());

		return "visitas";
	}
	
	@GetMapping("/visitastecnico")
	public String getVisitasByTecnico(HttpSession session, Model model){
		Integer userId = (Integer) session.getAttribute("userId");
		model.addAttribute("visitasTecnicos", visitasRepository.findByUsuariosId(userId));
		return "visitastecnico";
	}
	
	@GetMapping("/crearVisita")
	public String crearVisita(Model model, HttpSession session) {
		Visitas visita = new Visitas();
		model.addAttribute("visita", visita);
		model.addAttribute("clientes", clienteRepository.findAll());
		model.addAttribute("usuariosL", usuariosRepository.findAllWhererolId3());
		model.addAttribute("estados",estadosRepository.findAll());
		return "addvisita";
	}

	@PostMapping("/guardarVisita")
	public String guardarVisita(@ModelAttribute("visita") Visitas visita, Model model) {
		visitasRepository.save(visita);
		return "redirect:/visitas";
	}
	
	@PostMapping("/guardarVisitaTecnico")
	public String guardarVisitaTecnico(@ModelAttribute("visita") Visitas visita, Model model) {
		visitasRepository.save(visita);
		return "redirect:/visitastecnico";
	}


	
	@GetMapping("/actualizarVisita/{id}")
	public String actualizarVisita(@PathVariable("id") int id, Model model) {
		Optional<Visitas> temp = visitasRepository.findById(id);
		Visitas visita = temp.get();

		model.addAttribute("visita", visita);
		model.addAttribute("clientes", clienteRepository.findAll());
		model.addAttribute("usuariosL", usuariosRepository.findAllWhererolId3());
		model.addAttribute("estados",estadosRepository.findAll());
	    model.addAttribute("correoCliente", visita.getCliente().getEmail());

		return "actualizarvisita";
	}
	
	@GetMapping("/actualizarVisitaTecnico/{id}")
	public String actualizarVisitaTecnico(@PathVariable("id") int id, Model model) {
		Optional<Visitas> temp = visitasRepository.findById(id);
		Visitas visita = temp.get();

		model.addAttribute("visita", visita);
		model.addAttribute("clientes", clienteRepository.findAll());
		model.addAttribute("usuariosL", usuariosRepository.findAllWhererolId3());
		model.addAttribute("estados",estadosRepository.findAll());
	    model.addAttribute("correoCliente", visita.getCliente().getEmail());

		return "actualizarvisitatecnico";
	}
	
	@GetMapping("/borrarvisita/{id}")
	public String borrarVisita(@PathVariable("id") int id, Model model) {
		
		visitasRepository.deleteById(id);
		return "redirect:/visitas";
	}


	private boolean enviarCorreo(String correoCliente, Optional<Visitas> visita) {
		try {
		String asunto = "Detalles de la Visita";
		String cuerpo = "Estimado cliente," + visita.get().getCliente().getNombres() + ' ' + visita.get().getCliente().getApellidos() + "\n\nA continuación se detallan los datos de su visita:\n\n";
		
		cuerpo += "La Fecha y hora de inicio a la que el tecnico realizo la visita es: " + visita.get().getFecha_hora_inicio() + "\n";
		cuerpo += "La cual finalizo el dia y a la hora siguiente: " + visita.get().getFecha_hora_fin() + "\n";
		cuerpo += "En las siguientes coordenadas: " + visita.get().getCoordenadas_visita() + "\n";
		cuerpo += "Teniendo como resultado lo siguiente: " + visita.get().getReporte() + "\n";

		SimpleMailMessage mensaje = new SimpleMailMessage();
		mensaje.setTo(correoCliente);
		mensaje.setSubject(asunto);
		mensaje.setText(cuerpo);

		javaMailSender.send(mensaje);
		return true;
		
		}catch(Exception e) {
	        e.printStackTrace();
	        return false;
	    }
		
	}
	
	@GetMapping("/enviarCorreo/{id}")
	public String enviarCorreos(@PathVariable("id") Integer visitaId, Model model, RedirectAttributes redirectAttributes) {

	    Optional<Visitas> visita = visitasRepository.findById(visitaId);
	    

	    String correoCliente = visita.get().getCliente().getEmail();

	    boolean exitoEnvio = enviarCorreo(correoCliente, visita);
	    model.addAttribute("correoEnviado", exitoEnvio);
	    redirectAttributes.addFlashAttribute("correoEnviado", true);

		return "redirect:/visitas";

	
	}
	

}
