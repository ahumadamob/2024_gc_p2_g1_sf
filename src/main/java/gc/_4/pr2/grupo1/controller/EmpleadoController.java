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
import gc._4.pr2.grupo1.entity.Empleado;
import gc._4.pr2.grupo1.service.IEmpleadoService;

@RestController
public class EmpleadoController {
	
	@Autowired
	private IEmpleadoService service;
	
	@GetMapping("/empleados")
	public ResponseDTO<List<Empleado>> mostrarTodosEmpleados() {
		
		List<Empleado> listaEmpleados;
		listaEmpleados = service.mostrarTodos();
		
		//EJEMPLO DE INSTANCIA DE OBJETO
		ResponseDTO<List<Empleado>> dto;
		
		//EJEMPLO DE INICIALIZACION DE OBJETO
		dto = new ResponseDTO<List<Empleado>>();
		
		if (listaEmpleados.isEmpty()) {
			dto.setStatus(false);
			dto.setMessage("No hay datos disponibles.");
			dto.setData(null);
		} else {
			dto.setStatus(true);
			dto.setMessage("Se encontraron los siguientes empleados:");
			dto.setData(listaEmpleados);
		}
		return dto;
	}
	
	@GetMapping("/empleados/{id}")
	public ResponseDTO<Empleado> mostrarEmpleadoId(@PathVariable("id") Long id) {
		ResponseDTO<Empleado> dto = new ResponseDTO<>();
		
		if (service.exists(id)) {
			Empleado empleado = new Empleado();
			empleado = service.mostrarPorId(id);
			
			dto.setStatus(true);
			dto.setMessage("Empleado encontrado exitosamente.");
			dto.setData(empleado);
		} else {
			dto.setStatus(false);
			dto.setMessage("Empleado no encontrado con el ID: " + id);
			dto.setData(null);
		}
		
		return dto;
	}
	
	@PostMapping("/empleados")
	public ResponseDTO<Empleado> crearNuevoEmpleado(@RequestBody Empleado empleadoDesdePostman) {
		ResponseDTO<Empleado> dto = new ResponseDTO<>();
		
		//Verificar si el ID ya existe
		if (service.exists(empleadoDesdePostman.getId())) {
			dto.setStatus(false);
			dto.setMessage("Error! El empleado con ID " + empleadoDesdePostman.getId().toString() + " ya existe.");
			dto.setData(null);
		} else {
			//Crear nuevo empleado solo si no existe el ID
			dto.setStatus(true);
			dto.setMessage("Empleado creado exitosamente.");
			dto.setData(service.guardar(empleadoDesdePostman));
		}
		
		return dto;
	}
	
	@PutMapping("/empleados")
	public ResponseDTO<Empleado> actualizarEmpleado(@RequestBody Empleado empleadoDesdePostman) {
		ResponseDTO<Empleado> dto = new ResponseDTO<>();
		
		//Verificar si el empleado con el ID proporcionado existe
		if (service.exists(empleadoDesdePostman.getId())) {
			dto.setStatus(true);
			dto.setMessage("Empleado actualizado exitosamente.");
			dto.setData(service.guardar(empleadoDesdePostman));
			return dto;
		} else {
			dto.setStatus(true);
			dto.setMessage("Error! No se puede actualizar, el empleado con ID " + empleadoDesdePostman.getId().toString() + " no existe.");
			dto.setData(null);
			
			return dto;
		}
	}
	
	@DeleteMapping("/empleados/{ideliminar}")
	public ResponseDTO<?> borrarEmpleado(@PathVariable("ideliminar") Long id) {
		ResponseDTO<?> dto = new ResponseDTO<>();
		
		if (service.exists(id)) {
			dto.setStatus(true);
			dto.setMessage("Empleado eliminado exitosamente.");
			dto.setData(null);
			
			service.eliminarPorId(id);
		} else {
			dto.setStatus(false);
			dto.setMessage("Error! No se encontró el empleado con ID: " + id);
			dto.setData(null);
		}
			
		return dto;
	}
	
	@GetMapping("/empleados/existe/{id}")
	public ResponseDTO<Empleado> existeEmpleado(@PathVariable("id") Long id) {
		ResponseDTO<Empleado> dto = new ResponseDTO<>();
		
		if (service.exists(id)) {
			Empleado empleado = new Empleado();
			
			dto.setStatus(true);
			dto.setMessage("El empleado con el ID: " + id + " existe.");
			dto.setData(null);
		} else {
			dto.setStatus(false);
			dto.setMessage("El empleado con el ID: " + id + " no existe.");
			dto.setData(null);
		}
		
		return dto;
	}
}