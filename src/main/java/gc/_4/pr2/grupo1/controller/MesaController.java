package gc._4.pr2.grupo1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gc._4.pr2.grupo1.dto.ResponseDTO;
import gc._4.pr2.grupo1.entity.Mesa;
import gc._4.pr2.grupo1.service.IMesaService;







@RestController
public class MesaController {
	
	@Autowired
	private IMesaService service;
	
	@GetMapping("/Mesa")
	public ResponseDTO<List<Mesa>> mostrarTodasMesa(){
		List<Mesa> listaMesa;
		//ejemplo de instancia de objeto en alguna parte del controlador o servicio
		listaMesa = new ArrayList();
		//ejemplo de inicialización de objetos.
		listaMesa = service.mostrarTodos();
		
		ResponseDTO<List<Mesa>> dto;
		dto = new ResponseDTO<List<Mesa>>();		
		
		if(listaMesa.isEmpty()) {
			dto.setStatus(false);
			//ejemplo de encapsulamiento dentro de una clase
			dto.setMessage("No hay datos disponibles");
			dto.setData(null);
		}else {
			dto.setStatus(true);
			dto.setMessage("Se encontraron las siguientes Mesas");
			dto.setData(listaMesa);
		}		
		return dto;
	}
	
	@GetMapping("/Mesa/{id}")
	public ResponseDTO<Mesa> mostrarMesaPorId(@PathVariable("id") Long id){
		ResponseDTO<Mesa> dto= new ResponseDTO<Mesa>();
		if(service.existe(id)) {
			Mesa mesa = new Mesa();
			mesa = service.mostrarPorId(id);
			dto.setStatus(true);
			dto.setMessage("Encontrado");
			dto.setData(mesa);
			return dto;
		}else {			
			dto.setStatus(false);
			dto.setMessage("El elemento no existe");
			dto.setData(null);
			return dto;
		}
	}
	
	@PostMapping("/Mesa")
	public ResponseDTO<Mesa> crearNuevaMesa(@RequestBody Mesa mesaDesdeElServicio){
		ResponseDTO<Mesa> dto= new ResponseDTO<Mesa>();
		if(service.existe(mesaDesdeElServicio.getId())) {
			dto.setStatus(false);
			dto.setMessage("Este ID ya existe");
			dto.setData(null);
			return dto;
			
		}else {
			if(service.validaciónMesa(mesaDesdeElServicio)) {
				dto.setStatus(true);
				dto.setMessage("Mesa creada con exito");
				dto.setData(service.guardar(mesaDesdeElServicio));
				return dto;
			}else {
				dto.setStatus(false);
				dto.setMessage("Una mesa con capacidad mayor a 6 no puede estar no disponible");
				return dto;
			}
		
		}
	}
	
	
	@PutMapping("/Mesa")
	public ResponseDTO<Mesa> actualizarNuevaMesa(@RequestBody Mesa mesaDesdeElServicio){
		ResponseDTO<Mesa> dto= new ResponseDTO<Mesa>();
		if(service.existe(mesaDesdeElServicio.getId())) {
			dto.setStatus(true);
			dto.setMessage("Mesa actualizada con exito");
			dto.setData(service.guardar(mesaDesdeElServicio));
			return dto;
		}else {
			dto.setStatus(false);
			dto.setMessage("No se encontró el ID" + mesaDesdeElServicio.getId().toString());
			dto.setData(null);
			return dto;
		}		
	}
	
	
	@DeleteMapping("/Mesa/{id}")
	public ResponseDTO<Mesa> borrarMesa(@PathVariable("id") Long id){
		ResponseDTO<Mesa> dto= new ResponseDTO<Mesa>();
		if(service.existe(id)) {
			service.eliminarPorId(id);
			dto.setStatus(true);
			dto.setMessage("Mesa " + id.toString() + " eliminada");
			dto.setData(null);
			return dto;
		}else {
			dto.setStatus(false);
			dto.setMessage("No se encontró la Mesa ID: " + id.toString());
			dto.setData(null);
			return dto;
		}
	}
	

}
