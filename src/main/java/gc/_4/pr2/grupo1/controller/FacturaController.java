package gc._4.pr2.grupo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gc._4.pr2.grupo1.entity.Factura;
import gc._4.pr2.grupo1.service.IFacturaService;
import gc._4.pr2.grupo1.dto.ResponseDTO;



@RestController
public class FacturaController {
	@Autowired
	private IFacturaService service;
	
	
	@GetMapping("/pruebaok")
	public ResponseDTO<?> pruebaOk(){
		ResponseEntity response;
		ResponseDTO<?> dto = new ResponseDTO<>();
		dto.setCode(200);
		dto.setMessage("La prueba salio OK");	
		//response = new ResponseEntity("Prueba OK", HttpStatus.OK);
		//return response;
		return dto;
		
	}
	
	@GetMapping("/pruebanotfound")
	public ResponseEntity pruebaNotFound(){
		ResponseEntity response;
		response = new ResponseEntity("Mensaje de prueba para Not Found", HttpStatus.NOT_FOUND);
		return response;
		
	}	
	/*
	@GetMapping("/factura")
	public List<Factura> mostrarTodosFactura(){
		return service.mostrarTodos();
	}*/
	
	@GetMapping("/factura")
	public ResponseDTO<List<Factura>> mostrarTodosFactura(){
		ResponseDTO<List<Factura>> dto = new ResponseDTO<>();
		
		List<Factura> listaTodos;
		listaTodos = service.mostrarTodos();
		if(listaTodos.isEmpty()) {
			dto.setCode(400);
			dto.setMessage("Listado Vacio");
			dto.setData(listaTodos);
			//ResponseEntity response;
			//response = new ResponseEntity(null, HttpStatus.NO_CONTENT );
			//return response;			
		}else {
			dto.setCode(200);
			dto.setMessage("Listado");
			dto.setData(listaTodos);
			//ResponseEntity response;
			//response = new ResponseEntity(listaTodos, HttpStatus.OK );
			//return response;			
		}
		//return listaTodos;
		return dto;
			
		//return service.mostrarTodos();
	}
	
	/*
	@GetMapping("/factura/{id}")
	Factura mostrarFacturaId(@PathVariable("id") Long id){
		return service.mostrarPorId(id);
	}*/
	
	@GetMapping("/factura/{id}")
	ResponseDTO<?> mostrarFacturaId(@PathVariable("id") Long id){
		
		ResponseDTO<Factura> dto = new ResponseDTO<>();
		if(service.existe(id)) {
			dto.setCode(200);
			dto.setMessage("Encontrado");
			dto.setData(service.mostrarPorId(id));
			//return new ResponseEntity<Factura>(service.mostrarPorId(id), HttpStatus.OK );
		}else {
			dto.setCode(404);
			dto.setMessage("No Encontrado");
			//dto.setData(null);
			//return new ResponseEntity<String>("No encontrado", HttpStatus.NOT_FOUND);	
		}
		
		return dto;
	}
	
	/*
	@PostMapping("/factura")
	Factura crearNuevoFactura(@RequestBody Factura facturaDesdeElServicio){
		
		return service.guardar(facturaDesdeElServicio);
	}*/
	
	
	@PostMapping("/factura")
	ResponseEntity crearNuevoFactura(@RequestBody Factura facturaDesdeElServicio){
		if(service.existe(facturaDesdeElServicio.getId())) {
			ResponseEntity response;
			response = new ResponseEntity("Este elemento ya existe", HttpStatus.BAD_REQUEST );
			return response;			
			
		}else {
			ResponseEntity response;
			response = new ResponseEntity(service.guardar(facturaDesdeElServicio), HttpStatus.CREATED );
			return response;			
			//crear nuevo tipo
		}
		
	}
	
	/*
	@PutMapping("/factura")
	Factura actualizarNuevoFactura(@RequestBody Factura facturaDesdeElServicio){
		
		return service.guardar(facturaDesdeElServicio);
	}*/
	
	
	@PutMapping("/factura")
	ResponseEntity actualizarTipoNuevoPokemon(@RequestBody Factura facturaDesdeElServicio){
		if(service.existe(facturaDesdeElServicio.getId())) {
			ResponseEntity response;
			response = new ResponseEntity(service.guardar(facturaDesdeElServicio), HttpStatus.OK );
			return response;
		}else {
			ResponseEntity response;
			response = new ResponseEntity("Este elemento no existe, utilice el POST", HttpStatus.BAD_REQUEST );
			return response;
		}
		
	}
	
	
	/*@DeleteMapping("/factura/{id}")
	void borrarFactura(@PathVariable("id") Long id){
		service.eliminarPorId(id);
	}
	*/
	
	
	@DeleteMapping("/factura/{id}")
	ResponseEntity borrarFactura(@PathVariable("id") Long id){
		if(service.existe(id)) {
			ResponseEntity response;
			service.eliminarPorId(id);
			response = new ResponseEntity("Elemento eliminado", HttpStatus.OK );
			return response;
		}else {
			ResponseEntity response;
			response = new ResponseEntity("Este elemento no existe, No se puede eliminar", HttpStatus.BAD_REQUEST );
			return response;
		}
		
	}
	
	
	
	
	
	
}
