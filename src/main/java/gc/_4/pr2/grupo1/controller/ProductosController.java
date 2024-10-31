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

import gc._4.pr2.grupo1.dto.ResponseDTO;
import gc._4.pr2.grupo1.entity.Productos;
import gc._4.pr2.grupo1.service.IProductosService;



@RestController
public class ProductosController {

	@Autowired
	private IProductosService service;
	
	
	@GetMapping("/productos")
	public ResponseDTO<List<Productos>> mostrarTodosProductos() {
		
		//Instancia DTO y lista todos
		ResponseDTO<List<Productos>> dto = new ResponseDTO<>();
		List<Productos> listaTodos;
		
		//Inicialización de la listaTodos
		listaTodos = service.mostrarTodos();
		
		if(listaTodos.isEmpty()) {
			//Encapsulamiento
			dto.setStatus(false);
			dto.setMessage("Listado Vacío");
			dto.setData(listaTodos);
		}else {
			dto.setStatus(true);
			dto.setMessage("Listado");
			dto.setData(listaTodos);
		}
		return dto;
	}
	
	
	
	@GetMapping("/productos/{id}")
	ResponseDTO<?> mostrarProductosId(@PathVariable("id") Long id){
		ResponseDTO<Productos> dto = new ResponseDTO<>();
		
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
	
	@PostMapping("/productos")
	ResponseDTO<?> crearNuevoProductos(@RequestBody Productos productosDesdeElServicio){
		ResponseDTO<Productos> dto = new ResponseDTO<>();
		
		if(service.existe(productosDesdeElServicio.getId())){
			dto.setStatus(false);
			dto.setMessage("Este elemento ya existe");
			return dto;
		}else {
			dto.setStatus(true);
			dto.setMessage("Guardado");
			dto.setData(service.guardar(productosDesdeElServicio));
			return dto;
		}
	}
	
	@PutMapping("/productos")
	ResponseDTO<?> actualizarTipoNuevoProductos(@RequestBody Productos productosDesdeElServicio){
		ResponseDTO<Productos> dto = new ResponseDTO<>();
		if(service.existe(productosDesdeElServicio.getId())) {
			dto.setStatus(true);
			dto.setMessage("Modificado");
			dto.setData(service.guardar(productosDesdeElServicio));
			return dto;
		}else {
			dto.setStatus(false);
			dto.setMessage("Este elemento no existe, utilice el POST");
			return dto;
		}
	}

	@DeleteMapping("/productos/{id}")
	ResponseDTO<?> borrarProductos(@PathVariable("id") Long id){
		ResponseDTO<Productos> dto = new ResponseDTO<>();
		if(service.existe(id)) {
			dto.setStatus(true);
			dto.setMessage("Eliminado");
			service.eliminarPorId(id);
			return dto;
		}else {
			dto.setStatus(false);
			dto.setMessage("Este elemento no existe, no se puede eliminar");
			return dto;
		}
	}
}
