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

import gc._4.pr2.grupo1.entity.Mediodepago;
import gc._4.pr2.grupo1.service.MediodepagoService;
import gc._4.pr2.grupo1.dto.ResponseDTO;



@RestController
public class MediodepagoController {
	@Autowired
	private MediodepagoService service;
	
	
		
	@GetMapping("/mediodepago")
			//Ejemplo de instacia de objeto
	public ResponseDTO<List<Mediodepago>> mostrarTdosMediodepago(){
		//Ejemplo de inicialización de objeto
		ResponseDTO<List<Mediodepago>> dto = new ResponseDTO <>();
		
		List<Mediodepago> listaMediodePago;
		listaMediodePago = service.mostrarTodos();
		if(listaMediodePago.isEmpty()) {
			dto.setStatus(false);
			dto.setMessage("No hay medio de pago");
			dto.setData(null);
					
		}else {
			dto.setStatus(true);
			dto.setMessage("Se encontraron los siguientes medios de pagos");
			dto.setData(listaMediodePago);
			
		}
		return dto;
		
	}
	
	
	

	@GetMapping("/mediodepago/id")
	public ResponseDTO<Mediodepago> mostrarMediodepagoId(@PathVariable("id") Long id){
		ResponseDTO<Mediodepago> dto = new ResponseDTO <>();
		
		if(service.exists(id)) {
			Mediodepago mediodepago = new Mediodepago ();
			mediodepago = service.mostrarPorId(id);
			dto.setStatus(true);
			dto.setMessage("Se encontro un medio de pago exitosamente");
			dto.setData(mediodepago);
					
		}else {
			dto.setStatus(false);
			dto.setMessage("Medios de pagos no encontrado por el id "+ id);
			dto.setData(null);
			
		}
		return dto;
		
	}
	
	
	@PostMapping("/mediodepago")
	public ResponseDTO<Mediodepago> crearNuevoMediodepago(@RequestBody Mediodepago mediodepagoDesdePostman) {
		ResponseDTO<Mediodepago> dto = new ResponseDTO<>();
		
		//Verifica si el ID existe
		if  (service.exists(mediodepagoDesdePostman.getId())) {
			dto.setStatus(false);
			dto.setMessage("Error! El medio de pago con ID " + mediodepagoDesdePostman.getId().toString() + "ya existe,.");
			dto.setData(null);
		} else {
			//Crea nuevo medio de pago solo si no existe el ID
			dto.setStatus(true);
			dto.setMessage("Medio depago creado exitosamente.");
			dto.setData(service.guardar(mediodepagoDesdePostman));
		}
			return dto;
	}
	
	@PutMapping("/mediodepago")
	public ResponseDTO<Mediodepago> actualizarMediodepago(@RequestBody Mediodepago mediodepagoDesdePostman) {
		ResponseDTO<Mediodepago> dto = new ResponseDTO<>();
		// Verifica si el medio de pago con ID proporcionado existe
		if (service.exists(mediodepagoDesdePostman.getId())) {
			dto.setStatus(true);
			dto.setMessage("Medio de pagoo actualizado exitosanete.");
			dto.setData(service.guardar(mediodepagoDesdePostman));
			return dto;
		} else {
			dto.setStatus(true);
			dto.setMessage("Error! No se puede actualizar el medio de pago con ID " + mediodepagoDesdePostman.getId().toString() + " noexiste.");
			dto.setData(null);
			}
		return dto;
	}
	
	
	@DeleteMapping("/mediodepago/{ideliminar}")
	public ResponseDTO <? > borrarMediodepago(@PathVariable("ideliminar") Long id) {
	ResponseDTO <? > dto = new ResponseDTO<>();

	if (service.exists(id)) {
	dto.setStatus(true);
	dto.setMessage("Medio de pago eliminado exitosamente.");
	dto.setData(null);

	service.eliminarPorId(id);
	} else {
	dto.setStatus(false);
	dto. setMessage("Error! No se encontró el medio de pago con ID: " + id);
	dto.setData(null);
	}
	return dto;
	}
	
	@GetMapping("/mediodepago/existe/{id}")
	public ResponseDTO<Mediodepago> existeMediodepago(@PathVariable("id") Long id) { 
	ResponseDTO<Mediodepago> dto = new ResponseDTO<>();

	if (service.exists(id)) {
	Mediodepago mediodepago = new Mediodepago();

		dto.setStatus(true);
		dto.setMessage("El medio de pago con el ID: " + id + " existe.");
		dto.setData(null);
	} else {
		dto.setStatus(false);
		dto.setMessage("El medio de pago con el ID: " + id + " no existe.");
		dto.setData(null);
	}
	return dto;
	
	}
	}
