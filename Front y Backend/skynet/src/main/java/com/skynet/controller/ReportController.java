package com.skynet.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.skynet.entity.Clientes;
import com.skynet.entity.Roles;
import com.skynet.entity.Usuarios;
import com.skynet.entity.Visitas;
import com.skynet.repository.ClientesRepository;
import com.skynet.repository.RolesRepository;
import com.skynet.repository.UsuariosRepository;
import com.skynet.repository.VisitasRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Controller
public class ReportController {
	@Autowired
    private RolesRepository rolesRepository;
	@Autowired
	private UsuariosRepository usuariosRepository;

	@Autowired
    private ClientesRepository clienteRepository;
	
	@Autowired
	private VisitasRepository visitasRepository;
	
	 @GetMapping("/reporteRoles")
	    public void generateReporteRoles(HttpServletRequest request, HttpServletResponse response) {
	        try {
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            Document document = new Document();
	            PdfWriter.getInstance(document, baos);

	           
	            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
	    
	            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

	            document.open();


	            Paragraph title = new Paragraph("Informe de Roles", titleFont);
	            title.setAlignment(Element.ALIGN_CENTER);
	            title.setSpacingAfter(20);
	            document.add(title);

	            PdfPTable table = new PdfPTable(2);
	            table.setWidthPercentage(100);
	            table.setSpacingBefore(10);
	            table.setWidths(new float[]{1, 3});

	            PdfPCell cell;

	  
	            cell = new PdfPCell(new Phrase("ID", contentFont));
	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Nombre", contentFont));
	            table.addCell(cell);

	            List<Roles> roles = rolesRepository.findAll();
	            for (Roles rol : roles) {
	                String idRol =  String.valueOf(rol.getId_rol());
	                String nombre = rol.getNombre() != null ? rol.getNombre() : "";

	                cell = new PdfPCell(new Phrase(idRol, contentFont));
	                table.addCell(cell);

	                cell = new PdfPCell(new Phrase(nombre, contentFont));
	                table.addCell(cell);
	            }


	            document.add(table);

	            document.close();

	    
	            response.setHeader("Content-Disposition", "attachment; filename=Reporte_Roles.pdf");
	            response.setContentType("application/pdf");
	            response.setContentLength(baos.size());

	            ServletOutputStream outputStream = response.getOutputStream();
	            baos.writeTo(outputStream);
	            outputStream.flush();
	            outputStream.close();
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 
	   @GetMapping("/report-usuarios")
	    public void generateUsuariosReport(HttpServletRequest request, HttpServletResponse response) {
	        try {
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            Document document = new Document();
	            PdfWriter.getInstance(document, baos);

	            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
	            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

	            document.open();

	            Phrase title = new Phrase("Informe de Usuarios", titleFont);
	            PdfPTable titleTable = new PdfPTable(1);
	            titleTable.setWidthPercentage(100);
	            titleTable.setSpacingAfter(20);
	            titleTable.addCell(createCenteredCell(title));
	            document.add(titleTable);

	   
	            PdfPTable table = new PdfPTable(3);
	            table.setWidthPercentage(100);
	            table.setSpacingBefore(10);
	            table.setWidths(new float[]{1, 3, 2});

	            PdfPCell cell;

	            cell = new PdfPCell(new Phrase("ID", contentFont));
	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Nombre", contentFont));
	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Rol", contentFont));

	            table.addCell(cell);

	            List<Usuarios> usuarios = usuariosRepository.findAllUsuariosConRol();
	            for (Usuarios usuario : usuarios) {
	                String id = usuario.getId() != null ? String.valueOf(usuario.getId()) : "";
	                String nombre = usuario.getNombre() != null ? usuario.getNombre() : "";
	                String rolNombre = usuario.getRol() != null ? usuario.getRol().getNombre() : "";

	                cell = new PdfPCell(new Phrase(id, contentFont));
	                table.addCell(cell);

	                cell = new PdfPCell(new Phrase(nombre, contentFont));
	                table.addCell(cell);

	                cell = new PdfPCell(new Phrase(rolNombre, contentFont));
	                table.addCell(cell);
	            }

	            document.add(table);

	            document.close();

	            response.setHeader("Content-Disposition", "attachment; filename=usuarios_report.pdf");
	            response.setContentType("application/pdf");
	            response.setContentLength(baos.size());
	            response.getOutputStream().write(baos.toByteArray());
	            response.getOutputStream().flush();
	            response.getOutputStream().close();
	        } catch (DocumentException | IOException e) {
	            e.printStackTrace();
	        }
	    }

	
	    
	    @GetMapping("/report-clientes")
	    public void generateClientesReport(HttpServletRequest request, HttpServletResponse response) {
	        try {
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            Document document = new Document();
	            PdfWriter.getInstance(document, baos);

	  
	            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);

	            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

	            document.open();

	           
	            Phrase title = new Phrase("Informe de Clientes", titleFont);
	            PdfPTable titleTable = new PdfPTable(1);
	            titleTable.setWidthPercentage(100);
	            titleTable.setSpacingAfter(20);
	            titleTable.addCell(createCenteredCell(title));
	            document.add(titleTable);

	            PdfPTable table = new PdfPTable(6);
	            table.setWidthPercentage(100);
	            table.setSpacingBefore(10);
	            table.setWidths(new float[]{2, 2, 2, 2, 3, 3});

	            PdfPCell cell;


	            cell = new PdfPCell(new Phrase("Nombres", contentFont));
	    
	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Apellidos", contentFont));
	         
	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Email", contentFont));
	     
	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Teléfono", contentFont));
	        
	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Dirección", contentFont));
	       
	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Coordenadas", contentFont));
	      
	            table.addCell(cell);


	            List<Clientes> clientes = clienteRepository.findAll();
	            for (Clientes cliente : clientes) {
	                table.addCell(cliente.getNombres() != null ? cliente.getNombres() : "");
	                table.addCell(cliente.getApellidos() != null ? cliente.getApellidos() : "");
	                table.addCell(cliente.getEmail() != null ? cliente.getEmail() : "");
	                table.addCell(cliente.getTelefono() != null ? cliente.getTelefono() : "");
	                table.addCell(cliente.getDireccion() != null ? cliente.getDireccion() : "");
	                table.addCell(cliente.getCoordenadas() != null ? cliente.getCoordenadas() : "");
	            }


	            document.add(table);

	            document.close();

	
	            response.setHeader("Content-Disposition", "attachment; filename=clientes_report.pdf");
	            response.setContentType("application/pdf");
	            response.setContentLength(baos.size());


	            response.getOutputStream().write(baos.toByteArray());
	            response.getOutputStream().flush();
	            response.getOutputStream().close();
	        } catch (DocumentException | IOException e) {
	            e.printStackTrace();
	        }
	    }

	    @GetMapping("/reporte-visitas")
	    public void generateVisitasReport(HttpServletRequest request, HttpServletResponse response) {
	        try {
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            Document document = new Document();
	            PdfWriter.getInstance(document, baos);

	            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);

	            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

	            document.open();

	            Phrase title = new Phrase("Informe de Visitas", titleFont);
	            PdfPTable titleTable = new PdfPTable(1);
	            titleTable.setWidthPercentage(100);
	            titleTable.setSpacingAfter(20);
	            titleTable.addCell(createCenteredCell(title));
	            document.add(titleTable);

	    
	            PdfPTable table = new PdfPTable(8);
	            table.setWidthPercentage(100);
	            table.setSpacingBefore(10);
	            table.setWidths(new float[]{2, 2, 2, 2, 2, 2, 2, 2});

	            PdfPCell cell;
           cell = new PdfPCell(new Phrase("Cliente", contentFont));
	    
	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Técnico", contentFont));
	  
	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Fecha y Hora Inicio", contentFont));

	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Fecha y Hora Fin", contentFont));
	   
	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Coordenadas de Visita", contentFont));

	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Reporte", contentFont));

	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Enviado", contentFont));

	            table.addCell(cell);

	            cell = new PdfPCell(new Phrase("Estado", contentFont));
	            table.addCell(cell);

	          
	            List<Visitas> visitas = visitasRepository.findAll();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            for (Visitas visita : visitas) {
	                table.addCell(visita.getCliente() != null ? visita.getCliente().getNombres() : "");
	                table.addCell(visita.getUsuarios() != null ? visita.getUsuarios().getNombre() : "");
	                table.addCell(visita.getFecha_hora_inicio() != null ? visita.getFecha_hora_inicio().format(formatter) : "");
	                table.addCell(visita.getFecha_hora_fin() != null ? visita.getFecha_hora_fin().format(formatter) : "");
	                table.addCell(visita.getCoordenadas_visita() != null ? visita.getCoordenadas_visita() : "");
	                table.addCell(visita.getReporte() != null ? visita.getReporte() : "");
	                table.addCell(visita.getEnviado() ? "Si" : "No");
	                table.addCell(visita.getEstado() != null ? visita.getEstado().getNombre() : "");
	            }


	            document.add(table);

	            document.close();

	            response.setHeader("Content-Disposition", "attachment; filename=visitas_report.pdf");
	            response.setContentType("application/pdf");
	            response.setContentLength(baos.size());

	            response.getOutputStream().write(baos.toByteArray());
	            response.getOutputStream().flush();
	            response.getOutputStream().close();
	        } catch (DocumentException | IOException e) {
	            e.printStackTrace();
	        }
	    }
	    private PdfPCell createCenteredCell(Phrase phrase) {
	        PdfPCell cell = new PdfPCell(phrase);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        return cell;
	    }
	    
}
