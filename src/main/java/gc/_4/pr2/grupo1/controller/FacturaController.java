package gc._4.pr2.grupo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	/*Metodo Post con validacion*/
	@PostMapping("/api/factura")
	ResponseDTO<?> registrarFactura(@RequestBody Factura facturaDesdeElServicio){
		ResponseDTO<Factura> dto = new ResponseDTO<>();
			
		/*Validacion de ImpuestosAplicados>=0*/
		if(facturaDesdeElServicio.getImpuestosAplicados()<0) {
			dto.setStatus(false);
			dto.setMessage("El valor de impuestos aplicados debe ser mayor o igual a 0");
			dto.setData(null);
			return dto;	
			
		}else {
			dto.setStatus(true);
			dto.setMessage("Factura creada exitosamente");
			dto.setData(service.guardar(facturaDesdeElServicio));
			return dto;		
		}
		
	}
	
	
	
	@GetMapping("/factura")
	public ResponseDTO<List<Factura>> mostrarTodosFactura(){
		
		//instancia de dto y lista todos
		ResponseDTO<List<Factura>> dto = new ResponseDTO<>();
		List<Factura> listaTodos;
		
		//inicializacion de lista todos
		listaTodos = service.mostrarTodos();
		
		
		if(listaTodos.isEmpty()) {
			//encapsulamiento
			dto.setStatus(false);
			dto.setMessage("Listado Vacio");
			dto.setData(listaTodos);			
		}else {
			//encapsulamiento
			dto.setStatus(true);
			dto.setMessage("Listado");
			dto.setData(listaTodos);			
		}
		return dto;
		
	}
	
	@GetMapping("/factura/{id}")
	ResponseDTO<?> mostrarFacturaId(@PathVariable("id") Long id){
		ResponseDTO<Factura> dto = new ResponseDTO<>();
		if(service.existe(id)) {
			dto.setStatus(true);
			dto.setMessage("Encontrado");
			dto.setData(service.mostrarPorId(id));
		}else {
			dto.setStatus(false);
			dto.setMessage("No Encontrado");
		}
		return dto;
	}
	
	@PostMapping("/factura")
	ResponseDTO<?> crearNuevoFactura(@RequestBody Factura facturaDesdeElServicio){
		ResponseDTO<Factura> dto = new ResponseDTO<>();
		if(service.existe(facturaDesdeElServicio.getId())) {
			dto.setStatus(false);
			dto.setMessage("Este elemento ya existe");
			return dto;			
			
		}else {
			dto.setStatus(true);
			dto.setMessage("Guardado");
			dto.setData(service.guardar(facturaDesdeElServicio));
			return dto;		
		}
		
	}
	
	
	@PutMapping("/factura")
	ResponseDTO<?> actualizarFactura(@RequestBody Factura facturaDesdeElServicio){
		ResponseDTO<Factura> dto = new ResponseDTO<>();
		if(service.existe(facturaDesdeElServicio.getId())) {
			dto.setStatus(true);
			dto.setMessage("Modificado");
			dto.setData(service.guardar(facturaDesdeElServicio));
			return dto;
		}else {
			dto.setStatus(false);
			dto.setMessage("Este elemento no existe, utilice el POST");
			return dto;
		}
	}
	
	
	@DeleteMapping("/factura/{id}")
	ResponseDTO<?> borrarFactura(@PathVariable("id") Long id){
		ResponseDTO<Factura> dto = new ResponseDTO<>();
		if(service.existe(id)) {
			dto.setStatus(true);
			dto.setMessage("Eliminado");
			service.eliminarPorId(id);
			return dto;
		}else {
			dto.setStatus(false);
			dto.setMessage("Este elemento no existe, No se puede eliminar");
			return dto;
		}
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}
